package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.PharmacistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.SearchPharmacistParams;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.PharmacistRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    public Pharmacist findById(long id) {
        return this.pharmacistRepository.findById(id);
    }

    public List<Pharmacist> findAll() {
        return this.pharmacistRepository.findAll();
    }

    public PharmacistDTO registerPharmacist(PharmacistDTO newUser, Long id) {

        Pharmacist p = new Pharmacist();
        System.out.println(newUser.getUsername());

        p.setFirstName(newUser.getFirstName());
        p.setLastName(newUser.getLastName());
        p.setUsername(newUser.getUsername());
        p.setEmail(newUser.getEmail());
        p.setAddress(newUser.getAddress());
        p.setPhoneNumber(newUser.getPhoneNumber());
        p.setWorkingFrom(newUser.getWorkFrom());
        p.setWorkingTo(newUser.getWorkTo());
        p.setPrice(newUser.getPrice());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
        p.setPassword(hashedPass);

        Authority role = new Authority();
        role = authorityService.findByName("ROLE_PHARMACIST");
        p.setAuthority(role);

        Pharmacy pharm = pharmacyRepository.findOneById(id);
        p.setPharmacy(pharm);
        // List<Pharmacist> retPh = pharm.getPharmacists();
        // retPh.add(p);

        pharmacistRepository.save(p);
        pharmacyRepository.save(pharm);

        PharmacistDTO retUser = new PharmacistDTO();
        retUser.setId(p.getId());
        retUser.setFirstName(p.getFirstName());
        retUser.setLastName(p.getLastName());
        retUser.setUsername(p.getUsername());
        retUser.setEmail(p.getEmail());
        retUser.setPassword(p.getPassword());
        retUser.setAddress(p.getAddress());
        retUser.setPhoneNumber(p.getPhoneNumber());
        retUser.setWorkFrom(p.getWorkingFrom());
        retUser.setWorkTo(p.getWorkingTo());
        retUser.setPrice(p.getPrice());

        return retUser;
    }

    public List<PharmacistDTO> getAllPharmForPharmacy(Long id) {

        System.out.println("USAO OVDE U ALL PHARM");
        System.out.println(id);
        List<PharmacistDTO> retList = new ArrayList<>();
        List<Pharmacist> pharmaList = new ArrayList<>();

        pharmaList = pharmacistRepository.findAll();

        for (Pharmacist p : pharmaList) {
            if (p.getPharmacy().getId() == id) {
                PharmacistDTO dto = new PharmacistDTO();
                dto.setId(p.getId());
                System.out.println(p.getFirstName());
                dto.setFirstName(p.getFirstName());
                dto.setLastName(p.getLastName());
                dto.setFrom(p.getWorkingFrom().toString());
                dto.setTo(p.getWorkingTo().toString());
                dto.setPrice(p.getPrice());

                retList.add(dto);
            }
        }

        // Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        return retList;

    }

    // for counceling reservation
    public List<PharmacyDTO> searchPharmacist(SearchPharmacistParams params) {
        System.out.println("pronasao searchPharmacist");
        List<PharmacyDTO> retPharm = new ArrayList<>();

        List<Pharmacy> allPharm = pharmacyRepository.findAll();

        for (Pharmacy p : allPharm) {
            System.out.println("Usao u for za apoteke");
            List<Pharmacist> pharmacists = p.getPharmacists();
            List<Appointment> appointments = p.getPharmacyAppointments();
            if (!appointments.isEmpty() && !pharmacists.isEmpty()) {
                for (Appointment a : appointments) {

                    for (Pharmacist ph : pharmacists) {
                        System.out.println("Farmaceuti nisu prazni");
                        if (a.getPharmacist().getId() == ph.getId()) {
                            if (!a.getMeetingTime().equals(params.getTime())
                                    && !a.getDateOfAppointment().equals(params.getDate())) {
                                if (ph.getWorkingFrom().compareTo(params.getTime()) <= 0
                                        && ph.getWorkingTo().compareTo(params.getTime()) > 0) {
                                    System.out.println("ID se poklapa ali je farmaceut slobodan");

                                    PharmacyDTO dto = new PharmacyDTO();
                                    dto.setId(p.getId());
                                    dto.setName(p.getName());
                                    dto.setAddress(p.getAddress());
                                    dto.setRating(p.getRating());
                                    dto.setPrice(a.getPrice());

                                    retPharm.add(dto);
                                } else {
                                    System.out.println("Working time does not match...");
                                }
                            }
                        } else if (a.getPharmacist().getId() != ph.getId()) {

                            System.out.println("ID se ne poklapa, farmaceut sloobodan");

                            PharmacyDTO dto = new PharmacyDTO();
                            dto.setId(p.getId());
                            dto.setName(p.getName());
                            dto.setAddress(p.getAddress());
                            dto.setRating(p.getRating());
                            dto.setPrice(a.getPrice());

                            retPharm.add(dto);
                        }
                    }

                }
            } else if (appointments.isEmpty() && !pharmacists.isEmpty()) {
                // nema sastanaka zakazanih, znaci da su svi farmaceuti slobodni

                System.out.println("Usao u nema zakazanih sastanaka");
                PharmacyDTO dto = new PharmacyDTO();
                dto.setId(p.getId());
                dto.setName(p.getName());
                dto.setAddress(p.getAddress());
                dto.setRating(p.getRating());
                // dto.setPrice(a.getPrice());

                retPharm.add(dto);

            } else {
                System.out.println("Niti ima sastanaka niti su definisani farmaceuti u sklopu apoteke..");
            }
        }
        return retPharm;
    }

    // available pharmacists for counceling
    public List<PharmacistDTO> getAvailablePharmacists(Long id, SearchPharmacistParams params) {

        List<PharmacistDTO> retList = new ArrayList<>();

        Pharmacy p = pharmacyRepository.findOneById(id);

        List<Pharmacist> pharmacists = p.getPharmacists();
        List<Appointment> appointments = p.getPharmacyAppointments();

        if (appointments.isEmpty()) {

            for (Pharmacist ph : pharmacists) {
                System.out.println("RADNO VREME FARMACEUTA" + ph.getWorkingFrom() + ph.getWorkingTo());
                System.out.println("MOJE VREME" + params.getTime());
                if (ph.getWorkingFrom().compareTo(params.getTime()) <= 0
                        && ph.getWorkingTo().compareTo(params.getTime()) > 0) { // available
                    PharmacistDTO dto = new PharmacistDTO();
                    dto.setId(ph.getId());
                    dto.setLastName(ph.getLastName());
                    dto.setFirstName(ph.getFirstName());
                    dto.setFrom(ph.getWorkingFrom().toString());
                    dto.setTo(ph.getWorkingTo().toString());
                    // ubaciti rating

                    retList.add(dto);
                } else {
                    System.out.println("Working time does not match...");
                }
            }

        } else {

            for (Appointment a : appointments) {

                for (Pharmacist ph : pharmacists) {
                    System.out.println("Farmaceuti nisu prazni");
                    if (a.getPharmacist().getId() == ph.getId()) {
                        if (!a.getMeetingTime().equals(params.getTime())
                                && !a.getDateOfAppointment().equals(params.getDate())) {
                            if (ph.getWorkingFrom().compareTo(params.getTime()) <= 0
                                    && ph.getWorkingTo().compareTo(params.getTime()) > 0) {

                                PharmacistDTO dto = new PharmacistDTO();
                                dto.setId(ph.getId());
                                dto.setLastName(ph.getLastName());
                                dto.setFirstName(ph.getFirstName());
                                dto.setFrom(ph.getWorkingFrom().toString());
                                dto.setTo(ph.getWorkingTo().toString());
                                retList.add(dto);
                            }
                        } else {
                            System.out.println("Working time does not match...");
                        }
                    } else if (a.getPharmacist().getId() != ph.getId()) {

                        System.out.println("ID se ne poklapa, farmaceut sloobodan");

                        PharmacistDTO dto = new PharmacistDTO();
                        dto.setId(ph.getId());
                        dto.setLastName(ph.getLastName());
                        dto.setFirstName(ph.getFirstName());
                        dto.setFrom(ph.getWorkingFrom().toString());
                        dto.setTo(ph.getWorkingTo().toString());

                        retList.add(dto);
                    }
                }

            }

        }

        return retList;
    }

}

package com.example.pharmacybackend.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.pharmacybackend.dto.PharmacistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.SearchPharmacistParams;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.repository.PharmacistRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
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

    @Autowired
    private PharmacyAdministratorRepository adminsRepository;

    @Autowired
    private PharmacyService pharmacyService;

    public Pharmacist findById(long id) {
        return this.pharmacistRepository.findById(id);
    }

    public List<Pharmacist> findAll() {
        return this.pharmacistRepository.findAll();
    }

    @Transactional
    public Pharmacist savePharmacist(Pharmacist p) {
        return this.pharmacistRepository.save(p);
    }

    @Transactional
    public void updatePharmacistRating(Long id, double rate) {
        Pharmacist p = pharmacistRepository.findOneById(id);
        p.setRating(rate);
        this.savePharmacist(p);
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
        p.setApproved(true);

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

        this.savePharmacist(p);
        pharmacyService.savePharmacy(pharm);

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

        if (!pharmaList.isEmpty()) {
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
        } else {
            return retList;
        }

        // Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        return retList;

    }

    // for counceling reservation
    public List<PharmacyDTO> searchPharmacist(SearchPharmacistParams params) {

        List<PharmacyDTO> retPharm = new ArrayList<>();

        List<Pharmacy> allPharm = pharmacyRepository.findAll();

        for (Pharmacy p : allPharm) {

            List<Pharmacist> pharmacists = new ArrayList<>();
            List<Appointment> appointments = new ArrayList<>();
            pharmacists = p.getPharmacists();
            appointments = p.getPharmacyAppointments();

            if (!retPharm.isEmpty()) {
                for (PharmacyDTO dt : retPharm) {
                    System.out.println("ISPIS IS RETPHARM " + dt.getName());
                }
            }

            if (!appointments.isEmpty() && !pharmacists.isEmpty()) {
                for (Appointment a : appointments) {
                    if (a.getType().equals(AppointmentType.COUNCELING)) {
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

                                        if (retPharm.isEmpty()) {
                                            retPharm.add(dto);
                                        } else {
                                            for (PharmacyDTO dp : retPharm) {
                                                if (dp.getId() != dto.getId()) {
                                                    retPharm.add(dto);
                                                }
                                            }
                                        }

                                    } else {
                                        System.out.println("Working time does not match...");
                                    }
                                }
                            } else if (a.getPharmacist().getId() != ph.getId()) {

                                System.out.println("ID se ne poklapa, farmaceut sloobodan");
                                if ((params.getTime().compareTo(ph.getWorkingFrom()) > 0
                                        && params.getTime().compareTo(ph.getWorkingTo()) < 0)
                                        || (params.getTime().compareTo(ph.getWorkingFrom()) == 0)) {

                                    PharmacyDTO dto = new PharmacyDTO();
                                    dto.setId(p.getId());
                                    dto.setName(p.getName());
                                    dto.setAddress(p.getAddress());
                                    dto.setRating(p.getRating());
                                    dto.setPrice(a.getPrice());

                                    if (retPharm.isEmpty()) {
                                        retPharm.add(dto);
                                    } else {
                                        for (PharmacyDTO dp : retPharm) {
                                            if (dp.getId() != dto.getId()) {
                                                retPharm.add(dto);
                                            }
                                        }
                                    }

                                }

                            } else {
                                System.out.println("Time does not match pharmacist's time...");
                            }
                        }
                    } else {
                        System.out.println(
                                "Zakazani sastanci su kod dermatologa, znaci da je apoteka slobodna sto se tice farmaceuta...");

                        // List<Pharmacist> pharmPharm = p.getPharmacists();

                        for (Pharmacist pha : pharmacists) {
                            if ((params.getTime().compareTo(pha.getWorkingFrom()) > 0
                                    && params.getTime().compareTo(pha.getWorkingTo()) < 0)
                                    || (params.getTime().compareTo(pha.getWorkingFrom()) == 0)) {

                                PharmacyDTO dto = new PharmacyDTO();
                                dto.setId(p.getId());
                                dto.setName(p.getName());
                                dto.setAddress(p.getAddress());
                                dto.setRating(p.getRating());
                                dto.setPrice(a.getPrice());

                                if (retPharm.isEmpty()) {
                                    retPharm.add(dto);
                                } else {
                                    for (PharmacyDTO dp : retPharm) {
                                        if (dp.getId() != dto.getId()) {
                                            retPharm.add(dto);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Time does not match with working time of pharmacist");
                            }
                        }
                    }

                }
            } else if (appointments.isEmpty() && !pharmacists.isEmpty()) {
                // nema sastanaka zakazanih, znaci da su svi farmaceuti slobodni ali da li se
                // uklapa trazeno vreme u njihovo radno vreme?
                List<Pharmacist> pharm = new ArrayList<>();
                pharm = p.getPharmacists();
                for (Pharmacist phar : pharm) {

                    if ((params.getTime().compareTo(phar.getWorkingFrom()) > 0
                            && params.getTime().compareTo(phar.getWorkingTo()) < 0)
                            || (params.getTime().compareTo(phar.getWorkingFrom()) == 0)) {
                        System.out.println("Usao u nema zakazanih sastanaka");
                        PharmacyDTO dto = new PharmacyDTO();
                        dto.setId(p.getId());
                        dto.setName(p.getName());
                        dto.setAddress(p.getAddress());
                        dto.setRating(p.getRating());
                        // dto.setPrice(a.getPrice());
                        retPharm.add(dto);
                    } else {
                        System.out.println("TIME does not match...");
                    }

                }
            } else {
                System.out.println("Niti ima sastanaka niti su definisani farmaceuti u sklopu apoteke..");
            }
        }

        List<PharmacyDTO> newRetList = new ArrayList<>();

        for (PharmacyDTO t : retPharm) {
            if (!newRetList.contains(t)) {
                newRetList.add(t);
            }
        }

        return newRetList;
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
                    dto.setRating(ph.getRating());

                    if (retList.isEmpty()) {
                        retList.add(dto);
                    } else {
                        for (PharmacistDTO dp : retList) {
                            if (dp.getId() != dto.getId()) {
                                retList.add(dto);
                            }
                        }
                    }

                } else {
                    System.out.println("Working time does not match...");
                }
            }

        } else {

            for (Appointment a : appointments) {
                if (a.getType().equals(AppointmentType.COUNCELING)) {
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

                                    if (retList.isEmpty()) {
                                        retList.add(dto);
                                    } else {
                                        for (PharmacistDTO dp : retList) {
                                            if (dp.getId() != dto.getId()) {
                                                retList.add(dto);
                                            }
                                        }
                                    }
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

                            if (retList.isEmpty()) {
                                retList.add(dto);
                            } else {
                                for (PharmacistDTO dp : retList) {
                                    if (dp.getId() != dto.getId()) {
                                        retList.add(dto);
                                    }
                                }
                            }
                        }
                    }
                } else { // zakazani sastanak nije kod farmaceuta, dodaj sve farmaceute iz apoteke

                    for (Pharmacist ph : pharmacists) {
                        PharmacistDTO dto = new PharmacistDTO();
                        dto.setId(ph.getId());
                        dto.setLastName(ph.getLastName());
                        dto.setFirstName(ph.getFirstName());
                        dto.setFrom(ph.getWorkingFrom().toString());
                        dto.setTo(ph.getWorkingTo().toString());

                        if (retList.isEmpty()) {
                            retList.add(dto);
                        } else {
                            for (PharmacistDTO dp : retList) {
                                if (dp.getId() != dto.getId()) {
                                    retList.add(dto);
                                }
                            }
                        }

                    }

                }

            }

        }

        return retList;
    }

    public boolean removePharmacist(Long id, Long pharmID) {

        boolean removed = false;

        Pharmacy p = pharmacyRepository.findOneById(id);
        List<Appointment> allApp = p.getPharmacyAppointments();
        List<Pharmacist> pharmPharmacist = p.getPharmacists();

        if (!allApp.isEmpty()) {
            for (Appointment a : allApp) {
                if (a.getType().equals(AppointmentType.COUNCELING)) { // proveri samo savetovanja
                    if (a.getPharmacist().getId() == pharmID) {
                        System.out.println("Pharmacist has reserved appointment...");
                        return removed;
                    } else {
                        Pharmacist pharmacist = pharmacistRepository.findOneById(pharmID);
                        pharmPharmacist.remove(pharmacist);
                        pharmacyService.savePharmacy(p);
                        removed = true;
                        return removed;
                    }

                } else {
                    System.out.println("Not counceling, you can remove pharmacist...");
                    Pharmacist pharm = pharmacistRepository.findOneById(pharmID);
                    pharmPharmacist.remove(pharm);
                    pharmacyService.savePharmacy(p);
                    removed = true;
                    return removed;
                }
            }
        } else {
            System.out.println("There is no appointments in this pharmacy, pharmacist removed");
            Pharmacist pharm = pharmacistRepository.findOneById(pharmID);
            pharmPharmacist.remove(pharm);
            pharmacyService.savePharmacy(p);
            removed = true;
            return removed;
        }

        return removed;
    }

    // for patient
    public List<PharmacistDTO> getAllP() {

        List<Pharmacist> list = pharmacistRepository.findAll();
        List<PharmacistDTO> retList = new ArrayList<>();

        for (Pharmacist p : list) {

            PharmacistDTO dto = new PharmacistDTO();
            dto.setId(p.getId());
            dto.setAddress(p.getAddress());
            dto.setEmail(p.getEmail());
            dto.setFirstName(p.getFirstName());
            dto.setLastName(p.getLastName());
            dto.setPhoneNumber(p.getPhoneNumber());
            dto.setFrom(p.getWorkingFrom().toString());
            dto.setTo(p.getWorkingTo().toString());
            dto.setPrice(p.getPrice());
            dto.setPharmacyName(p.getPharmacy().getName());
            dto.setUsername(p.getUsername());
            dto.setRating(p.getRating());

            retList.add(dto);

        }

        return retList;
    }

    // for pharmacy admin
    public List<PharmacistDTO> getAdmins(Long id) {

        List<PharmacistDTO> retList = new ArrayList<>();

        PharmacyAdministrator admin = adminsRepository.findOneById(id);
        List<Pharmacy> pharmList = pharmacyRepository.findAll();

        for (Pharmacy p : pharmList) {

            List<PharmacyAdministrator> admins = new ArrayList<>();
            admins = p.getPharmacyAdministrators();

            for (PharmacyAdministrator a : admins) {
                if (a.getId() == id) {

                    List<Pharmacist> list = new ArrayList<>();
                    list = p.getPharmacists();

                    for (Pharmacist ph : list) {
                        PharmacistDTO dto = new PharmacistDTO();
                        dto.setId(ph.getId());
                        dto.setAddress(ph.getAddress());
                        dto.setEmail(ph.getEmail());
                        dto.setFirstName(ph.getFirstName());
                        dto.setLastName(ph.getLastName());
                        dto.setPhoneNumber(ph.getPhoneNumber());
                        dto.setFrom(ph.getWorkingFrom().toString());
                        dto.setTo(ph.getWorkingTo().toString());
                        dto.setPrice(ph.getPrice());
                        dto.setPharmacyName(ph.getPharmacy().getName());
                        dto.setUsername(ph.getUsername());
                        dto.setRating(ph.getRating());

                        retList.add(dto);
                    }
                }

            }

        }
        return retList;

    }

}

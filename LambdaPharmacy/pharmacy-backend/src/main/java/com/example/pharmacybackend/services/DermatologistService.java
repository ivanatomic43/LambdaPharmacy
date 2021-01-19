package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    public Dermatologist findById(long id) {
        return this.dermatologistRepository.findById(id);
    }

    public List<Dermatologist> findAll() {
        return this.dermatologistRepository.findAll();
    }

    public Dermatologist registerDermatologist(UserRequestDTO newUser) {

        Dermatologist d = new Dermatologist();
        System.out.println(newUser.getUsername());

        d.setUsername(newUser.getUsername());
        d.setFirstName(newUser.getFirstName());
        d.setLastName(newUser.getLastName());
        d.setEmail(newUser.getEmail());
        d.setAddress(newUser.getAddress());
        d.setPhoneNumber(newUser.getPhoneNumber());
        d.setApproved(true);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
        d.setPassword(hashedPass);

        Authority role = new Authority();
        role = authorityService.findByName("ROLE_DERMATOLOGIST");
        d.setAuthority(role);

        this.dermatologistRepository.save(d);

        return d;
    }

    // employ dermatologist
    public DermatologistDTO addDermatologist(DermatologistDTO dto, Long id) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        Dermatologist d = dermatologistRepository.findOneById(dto.getId());

        EmployedDermatologist ed = new EmployedDermatologist();
        ed.setDateFrom(dto.getDateFrom());
        ed.setDateTo(dto.getDateTo());
        ed.setWorkFrom(dto.getWorkFrom());
        ed.setWorkTo(dto.getWorkTo());
        ed.setPrice(dto.getPrice());
        ed.setRating(0);
        ed.setPharmacy(pharmacy);
        ed.setDermatologist(d);

        List<Dermatologist> retDer = pharmacy.getDermatologists();

        retDer.add(d);

        dermatologistRepository.save(d);
        pharmacyRepository.save(pharmacy);
        employedDermatologistRepository.save(ed);

        DermatologistDTO retUser = new DermatologistDTO();
        retUser.setId(d.getId());
        retUser.setFirstName(d.getFirstName());
        retUser.setLastName(d.getLastName());
        retUser.setFrom(ed.getWorkFrom().toString());
        retUser.setTo(ed.getWorkTo().toString());
        retUser.setDateFromm(ed.getDateFrom().toString());
        retUser.setDateToo(ed.getDateTo().toString());
        retUser.setPrice(ed.getPrice());
        retUser.setRating(ed.getRating());

        return retUser;

    }

    public List<DermatologistDTO> getAllDermForPharmacy(Long id) {

        List<DermatologistDTO> retList = new ArrayList<>();

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);
        List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();

        for (EmployedDermatologist ed : derm) {
            if (ed.getPharmacy().getId() == id) {

                DermatologistDTO dto = new DermatologistDTO();
                dto.setId(ed.getDermatologist().getId());
                dto.setFirstName(ed.getDermatologist().getFirstName());
                dto.setLastName(ed.getDermatologist().getLastName());
                dto.setDateFromm(ed.getDateFrom().toString());
                dto.setDateToo(ed.getDateTo().toString());
                dto.setPrice(ed.getPrice());
                dto.setRating(ed.getRating());
                dto.setFrom(ed.getWorkFrom().toString());
                dto.setTo(ed.getWorkTo().toString());

                retList.add(dto);

            }
        }
        return retList;
    }

    public boolean removeDermatologist(Long id, Long dermID) {

        boolean removed = false;

        Pharmacy p = pharmacyRepository.findOneById(id);
        List<Appointment> allApp = p.getPharmacyAppointments();
        List<Dermatologist> pharmDermatologist = p.getDermatologists();

        if (!allApp.isEmpty()) {
            for (Appointment a : allApp) {
                if (a.getDermatologist().getId() == dermID) {
                    System.out.println("Dermatologist has reserved appointment...");
                    return removed;
                } else {
                    Dermatologist derm = dermatologistRepository.findOneById(dermID);
                    pharmDermatologist.remove(derm);
                    pharmacyRepository.save(p);
                    removed = true;
                    return removed;
                }
            }
        } else {
            System.out.println("There is no appointments in this pharmacy, dermatologist removed");
            Dermatologist derm = dermatologistRepository.findOneById(dermID);
            pharmDermatologist.remove(derm);
            pharmacyRepository.save(p);
            removed = true;
            return removed;
        }

        return removed;
    }

    public List<DermatologistDTO> getDermatologistPatient() {

        List<DermatologistDTO> retList = new ArrayList<>();

        System.out.println("USAO U GET DERM PATIENT SERVICE");
        List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();
        List<Pharmacy> pharmList = pharmacyRepository.findAll();

        for (EmployedDermatologist d : derm) {

            DermatologistDTO dto = new DermatologistDTO();
            dto.setId(d.getDermatologist().getId());
            dto.setFirstName(d.getDermatologist().getFirstName());
            dto.setLastName(d.getDermatologist().getLastName());
            // dto.setUsername(d.getUsername());
            dto.setDateFromm(d.getDateFrom().toString());
            dto.setDateToo(d.getDateTo().toString());
            dto.setFrom(d.getWorkFrom().toString());
            dto.setTo(d.getWorkTo().toString());
            dto.setRating(d.getRating());
            dto.setPrice(d.getPrice());
            dto.setPharmacyName(d.getPharmacy().getName());

            retList.add(dto);

        }
        return retList;
    }
}

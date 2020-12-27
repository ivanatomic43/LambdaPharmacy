package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.PharmacistDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
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

        return retUser;
    }

    public List<PharmacistDTO> getAllPharmForPharmacy(Long id) {

        System.out.println("USAO OVDE U ALL PHARM");
        System.out.println(id);
        List<PharmacistDTO> retList = new ArrayList<>();
        List<Pharmacist> pharmaList = new ArrayList();

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

                retList.add(dto);
            }
        }

        // Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        return retList;

    }

}
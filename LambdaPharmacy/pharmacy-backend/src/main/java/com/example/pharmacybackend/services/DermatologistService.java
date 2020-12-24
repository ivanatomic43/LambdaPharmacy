package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.DermatologistRepository;
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

    public DermatologistDTO addDermatologist(DermatologistDTO dto, Long id) {

        System.out.println(
                "PODACI: " + dto.getId() + dto.getDateFrom() + dto.getDateTo() + dto.getWorkFrom() + dto.getWorkTo());

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        Dermatologist d = dermatologistRepository.findOneById(dto.getId());

        d.setDateFrom(dto.getDateFrom());
        d.setDateTo(dto.getDateTo());
        d.setWorkingFrom(dto.getWorkFrom());
        d.setWorkingTo(dto.getWorkTo());

        List<Dermatologist> retDer = pharmacy.getDermatologists();

        retDer.add(d);

        dermatologistRepository.save(d);
        pharmacyRepository.save(pharmacy);

        DermatologistDTO retUser = new DermatologistDTO();
        retUser.setId(d.getId());
        retUser.setFirstName(d.getFirstName());
        retUser.setLastName(d.getLastName());
        retUser.setWorkFrom(d.getWorkingFrom());
        retUser.setWorkTo(d.getWorkingTo());

        return retUser;

    }

    public List<DermatologistDTO> getAllDermForPharmacy(Long id) {

        List<DermatologistDTO> retList = new ArrayList<>();

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        List<Dermatologist> retDer = pharmacy.getDermatologists();

        for (Dermatologist d : retDer) {

            DermatologistDTO dto = new DermatologistDTO();
            dto.setId(d.getId());
            dto.setFirstName(d.getFirstName());
            dto.setLastName(d.getLastName());
            dto.setWorkFrom(d.getWorkingFrom());
            dto.setWorkTo(d.getWorkingTo());

            retList.add(dto);
        }

        return retList;

    }
}

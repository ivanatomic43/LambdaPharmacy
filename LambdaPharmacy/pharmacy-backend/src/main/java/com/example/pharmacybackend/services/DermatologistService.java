package com.example.pharmacybackend.services;

import java.util.List;

import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.repository.DermatologistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private AuthorityService authorityService;

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

}

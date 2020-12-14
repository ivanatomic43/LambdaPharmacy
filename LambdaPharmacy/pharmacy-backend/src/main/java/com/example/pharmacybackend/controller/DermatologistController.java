package com.example.pharmacybackend.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.owasp.encoder.Encode;

import com.example.pharmacybackend.dto.*;
import com.example.pharmacybackend.exceptions.ResourceConflictException;
import com.example.pharmacybackend.model.*;
import com.example.pharmacybackend.security.*;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.AuthorityService;
import com.example.pharmacybackend.services.CustomUserDetailsService;
import com.example.pharmacybackend.services.EmailService;
import com.example.pharmacybackend.services.PatientService;
import com.example.pharmacybackend.services.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/dermatologist")
public class DermatologistController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/getAllDermatologists", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACY_ADMIN')")
    public ResponseEntity<?> getAllDermatologists() {

        List<UserDTO> list = userService.getAllDermatologist();

        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

}

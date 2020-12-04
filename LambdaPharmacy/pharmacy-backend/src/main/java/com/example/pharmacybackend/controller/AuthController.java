package com.example.pharmacybackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.owasp.encoder.Encode;

import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.exceptions.ResourceConflictException;
import com.example.pharmacybackend.model.*;
import com.example.pharmacybackend.security.*;
import com.example.pharmacybackend.repository.AuthorityRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.CustomUserDetailsService;
import com.example.pharmacybackend.services.EmailService;
import com.example.pharmacybackend.services.PatientService;
import com.example.pharmacybackend.services.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		
		//System.out.println(	"REQUEST: " + hr.getHeader("TokenAuthBic"));
		// 
		
		User found = userService.findByUsername(Encode.forHtml(authenticationRequest.getUsername()));
		
		if(found == null) {
			System.out.println("Username does not exist!");
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.UNAUTHORIZED);
		}
		
		if(!found.isApproved() && (!found.isFirstLogin())) {
			System.out.println("User didn't confirm registration");
			System.out.println(found.isApproved());
			System.out.println(found.isFirstLogin());
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.FORBIDDEN);
		}
		
		if(!found.isFirstLogin() && found.isApproved()) {
			System.out.println("User se loguje prvi put...");
			found.setFirstLogin(true);
			
			
		}
		
		
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		
		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String token = tokenUtils.generateToken(user.getUsername());
		
		
	
		int expiresIn = tokenUtils.getExpiredIn() * 1000000000;
		List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

		// Vrati token kao odgovor na uspesnu autentifikaciju*/
		return ResponseEntity.ok(new UserTokenState(user.getUsername(), token, expiresIn, authorities));
	}
	
	@PostMapping("/registerPatient")
	public ResponseEntity<?> registerPatient(@RequestBody UserRequestDTO userRequest){
		
		System.out.println("Usao u registraciju pacijenta");
		
		User existUser = this.userService.findByUsername(userRequest.getUsername());
		System.out.println("Registruje se pacijent: " + userRequest.getUsername());
		if(existUser!= null) {
			throw new ResourceConflictException(existUser.getId(), "Username already exists");
			
			
		}
		
		Patient existPatient = this.patientService.findPatientByUsername(userRequest.getUsername());
		if(existPatient != null) {
			throw new ResourceConflictException(existPatient.getId(), "Username already exists");
		}
		
		
		
		Patient patient = this.patientService.registerPatient(userRequest);
		this.emailService.sendActivationLink(patient);
		this.patientRepository.save(patient);
		System.out.println(patient.isApproved());
		
		 if (patient == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(patient, HttpStatus.CREATED);
	
	}
	
	
}

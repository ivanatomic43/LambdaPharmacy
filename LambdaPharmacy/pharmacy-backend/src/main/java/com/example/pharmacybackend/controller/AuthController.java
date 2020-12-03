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

import com.example.pharmacybackend.model.*;
import com.example.pharmacybackend.security.*;
import com.example.pharmacybackend.repository.AuthorityRepository;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.CustomUserDetailsService;
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
		
		if(!found.isApproved()) {
			System.out.println("User didn't confirm registration");
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.FORBIDDEN);
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
	
}

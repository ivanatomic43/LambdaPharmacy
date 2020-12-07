package com.example.pharmacybackend.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.owasp.encoder.Encode;

import com.example.pharmacybackend.dto.*;
import com.example.pharmacybackend.exceptions.ResourceConflictException;
import com.example.pharmacybackend.model.*;
import com.example.pharmacybackend.security.*;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.AuthorityService;
import com.example.pharmacybackend.services.EmailService;
import com.example.pharmacybackend.services.PatientService;
import com.example.pharmacybackend.services.UserServiceImpl;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PatientService patientService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AuthorityService authorityService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		// System.out.println( "REQUEST: " + hr.getHeader("TokenAuthBic"));
		//

		User found = userService.findByUsername(Encode.forHtml(authenticationRequest.getUsername()));

		if (found == null) {
			System.out.println("Username does not exist!");
			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.UNAUTHORIZED);
		}

		if (!found.isApproved()) {
			System.out.println("User didn't confirm registration");

			return new ResponseEntity<>(new UserTokenState("error", 0), HttpStatus.FORBIDDEN);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String token = tokenUtils.generateToken(user.getUsername());

		int expiresIn = tokenUtils.getExpiredIn() * 1000000000;
		List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		// Vrati token kao odgovor na uspesnu autentifikaciju*/
		return ResponseEntity.ok(new UserTokenState(user.getUsername(), token, expiresIn, authorities));
	}

	@PostMapping("/registerPatient")
	public ResponseEntity<?> registerPatient(@RequestBody UserRequestDTO userRequest) {

		System.out.println("Usao u registraciju pacijenta");

		User existUser = this.userService.findByUsername(userRequest.getUsername());
		System.out.println("Registruje se pacijent: " + userRequest.getUsername());
		if (existUser != null) {
			throw new ResourceConflictException(existUser.getId(), "Username already exists");

		}

		Patient existPatient = this.patientService.findPatientByUsername(userRequest.getUsername());
		if (existPatient != null) {
			throw new ResourceConflictException(existPatient.getId(), "Username already exists");
		}

		Patient patient = this.patientService.registerPatient(userRequest);
		this.emailService.sendActivationLink(patient);

		if (patient == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(patient, HttpStatus.CREATED);

	}

	@RequestMapping("/registrationConfirm/{email}")
	public void confirmation(@PathVariable("email") String email, HttpServletResponse response) throws IOException {

		System.out.println("EMAIL " + email);
		User confirm = userService.findByEmail(email);
		if (confirm == null || confirm.isApproved() == true) {

			if (confirm == null)
				System.out.println("User is null.");
			else if (confirm.isApproved() == true)
				System.out.println("User is already approved.");
		} else {
			confirm.setApproved(true);
			Authority role = null;
			role = authorityService.findByName("ROLE_PATIENT");

			if (role == null) {
				role = new Authority();
				role.setName("ROLE_PATIENT");
				authorityService.save(role);
			}

			confirm.setAuthority(role);
			// authorityService.updateUserAuthority(potvrda.getId(), uloga.getId());
			userService.updateActivation(true, confirm.getId());
			response.sendRedirect("http://localhost:4200/login");
			System.out.println("USPESNO AKTIVIRAN");

		}
	}

	@GetMapping("/logout")
	public void logout() {
		System.out.println("LOGOUT");
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
	}

	@GetMapping("/getLoggedUser")
	public ResponseEntity<?> getLoggedUser(HttpServletRequest request) {

		System.out.println("Usao u getLoggedUser ovaj username: "
				+ SecurityContextHolder.getContext().getAuthentication().getName());

		User user = this.userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		if (user != null) {
			List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());
			System.out.println("Id:" + user.getId());

			// System.out.println("request get header: " +
			// request.getHeader("Authorization"));
			UserTokenState loggedUser = new UserTokenState(user.getUsername(), request.getHeader("TokenAuthBic"),
					authorities);
			System.out.println(loggedUser.getUsername());

			return new ResponseEntity<UserTokenState>(loggedUser, HttpStatus.OK);
		} else {
			System.out.println("eles");
			return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/getMyUser", method = RequestMethod.POST)
	public ResponseEntity<?> getMyUser(@RequestBody UserDTO person) {
		System.out.println("usao u getmyuser" + person.getUsername());

		User personRet = userService.findOneByUsername(person.getUsername());

		return new ResponseEntity<>(new UserDTO(personRet), HttpStatus.OK);

	}

}
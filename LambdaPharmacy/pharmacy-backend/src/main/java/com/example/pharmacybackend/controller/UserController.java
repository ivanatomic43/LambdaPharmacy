package com.example.pharmacybackend.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.model.*;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.*;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	TokenUtils tokenUtils;

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	public User user(Principal user) {

		return this.userService.findByUsername(user.getName());
	}

	@GetMapping("/foo")
	public Map<String, String> getFoo() {
		Map<String, String> fooObj = new HashMap<>();
		fooObj.put("foo", "bar");
		return fooObj;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<?> updateProfile(@RequestBody UserDTO user) {

		UserDTO retUser = userService.updateUser(user);

		if (retUser == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(retUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkIfSub/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> checkIfSub(@PathVariable("id") Long id, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean sub = userService.checkIfSub(id, user.getId());

		return new ResponseEntity<>(sub, HttpStatus.OK);
	}

}
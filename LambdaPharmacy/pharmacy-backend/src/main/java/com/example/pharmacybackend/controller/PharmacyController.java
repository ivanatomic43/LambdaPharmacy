package com.example.pharmacybackend.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.description.type.PackageDescription;

import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.PromotionDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Image;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.ImageService;
import com.example.pharmacybackend.services.PharmacyService;
import com.example.pharmacybackend.services.UserServiceImpl;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private PharmacyAdministratorRepository pharmacyAdminRep;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/getAllPharmacies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPharmacies() {

		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		List<PharmacyDTO> retPha = new ArrayList<>();
		List<PharmacyAdministrator> admins = pharmacyAdminRep.findAll();

		for (Pharmacy p : pharmacies) {

			PharmacyDTO newPha = new PharmacyDTO(p.getId(), p.getAddress(), p.getDescription(), p.getName(),
					p.getRating());

			/*
			 * for (PharmacyAdministrator pa : admins) { if() if (pa.getPharmacy().getId()
			 * == p.getId()) { newPha.setFirstName(pa.getFirstName());
			 * newPha.setLastName(pa.getLastName());
			 * newPha.setPharmacyAdministrator(pa.getId()); } }
			 */
			retPha.add(newPha);

		}

		return new ResponseEntity<>(retPha, HttpStatus.OK);
	}

	@RequestMapping(value = "/createPharmacy", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> createPharmacy(@RequestBody PharmacyDTO pharmacy, HttpServletRequest request) {
		System.out.println(pharmacy.getName() + pharmacy.getAddress() + pharmacy.getDescription());
		PharmacyDTO createdPharmacy = pharmacyService.createPharmacy(pharmacy);

		if (createdPharmacy == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(createdPharmacy, HttpStatus.OK);

	}

	@PostMapping(value = "/addImages/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addImages(@PathVariable("id") Long id, @RequestPart("image") MultipartFile file)
			throws IOException {

		Pharmacy pharm = pharmacyService.findById(id);

		byte[] data = file.getBytes();
		Image image = new Image(data, pharm);
		if (image == null) {
			System.out.println("NULL IMAGE");
		}
		this.imageService.save(image);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> allImages(@PathVariable("id") Long id) throws UnsupportedEncodingException {

		System.out.println("Usao u get images sa id : " + id);

		Pharmacy ph = this.pharmacyService.findById(id);
		Image img = this.imageService.getImage(ph);

		byte[] encodeBase64 = Base64.getEncoder().encode(img.getUrl());
		String encoded = new String(encodeBase64, "UTF-8");
		String newSt = encoded.substring(1);

		return new ResponseEntity<>(newSt, HttpStatus.OK);
	}

	@RequestMapping(value = "/getPharmacy/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPharmacy(@PathVariable("id") Long id) {
		System.out.println("USAO U GET PH" + id);

		Pharmacy p = this.pharmacyService.getPharmacy(id);

		PharmacyDTO dto = new PharmacyDTO(p);

		if (dto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAdministrators", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> getAdministrators() {

		List<UserDTO> list = userService.getAdministrators();

		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/registerPharmacyAdministrator", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody UserRequestDTO userRequest) {

		User exist = userService.findByUsername(userRequest.getUsername());

		if (exist != null) {

			return new ResponseEntity<>(exist, HttpStatus.CONFLICT);
		}

		boolean done = pharmacyService.registerAdmin(userRequest);

		if (!done) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(done, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAdminsForPharmacy/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAdminsForPharmacy(@PathVariable("id") Long id) {

		List<UserDTO> list = pharmacyService.getAdminsForPharmacy(id);

		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/subscribe/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> subscribeForNewsletter(@PathVariable("id") Long id, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean subscribed = pharmacyService.subscribeForNewsletter(id, user.getId());

		return new ResponseEntity<>(subscribed, HttpStatus.OK);

	}

	@RequestMapping(value = "/getSubPharmacies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSubPharmacies(HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		List<PharmacyDTO> list = pharmacyService.getSubPharmacies(user.getId());

		if (list.isEmpty())
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/fetchAllPromotions/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchAllPromotions(@PathVariable("id") Long id) {

		List<PromotionDTO> list = pharmacyService.fetchAllPromotions(id);

		if (list.isEmpty())
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/createPromotion/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchAllPromotions(@PathVariable("id") Long id, @RequestBody PromotionDTO newProm) {

		PromotionDTO newPromotion = pharmacyService.createPromotion(id, newProm);

		if (newPromotion == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(newPromotion, HttpStatus.OK);

	}

}

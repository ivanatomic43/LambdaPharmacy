package com.example.pharmacybackend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.dto.PharmacistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.SearchPharmacistParams;
import com.example.pharmacybackend.dto.SearchUserDTO;
import com.example.pharmacybackend.dto.SimpleSearchDTO;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.MedicineService;
import com.example.pharmacybackend.services.PharmacistService;
import com.example.pharmacybackend.services.PharmacyService;
import com.example.pharmacybackend.services.UserServiceImpl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private PharmacistService pharmacistService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/searchMedicine/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchMedicine(@PathVariable("name") String name, HttpServletRequest httpRequest) {

		List<MedicineDTO> medicines = medicineService.searchMedicine(name);

		if (medicines.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchPharmacy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchPharmacy(@RequestBody SimpleSearchDTO p) {

		System.out.println("SIMPLE SEARCHDTO" + p.toString());

		String name = "";
		String location = "";
		// double rating = 0;

		if (p.getPharmacyName() != null) {
			name = p.getPharmacyName();
		}
		if (p.getPharmacyLocation() != null) {
			location = p.getPharmacyLocation();
		}
		/*
		 * if(p.getPharmacyRating() != 0) { rating = p.getPharmacyRating(); }
		 */
		List<PharmacyDTO> pharmacies = pharmacyService.searchPharmacy(name, location);

		if (pharmacies.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchPharmacist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchPharmacist(@RequestBody SearchPharmacistParams sp) {

		System.out.println(sp.getDate());
		System.out.println(sp.getTime());
		List<PharmacyDTO> pharmacies = pharmacistService.searchPharmacist(sp);

		/*
		 * if (pharmacies.isEmpty()) {
		 * 
		 * return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
		 */

		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}

	@RequestMapping(value = "/searchPharmByParams", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PATIENT') or hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> searchPharmByParams(@RequestBody SearchUserDTO myUser, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		List<PharmacistDTO> retList = new ArrayList<>();
		List<PharmacistDTO> list = new ArrayList<>();

		if (user.getAuthority().getName().equals("ROLE_PATIENT")) {
			retList = pharmacistService.getAllP();

			for (PharmacistDTO p : retList) {
				if (p.getFirstName().toLowerCase().equals(myUser.getSearchName().toLowerCase())
						&& p.getLastName().toLowerCase().equals(myUser.getSearchSurname().toLowerCase())) {
					list.add(p);
				} else {
					System.out.println("Ne slazu se");
				}
			}
		}

		if (user.getAuthority().getName().equals("ROLE_PHARMACY_ADMIN")) {
			retList = pharmacistService.getAdmins(user.getId());

			for (PharmacistDTO p : retList) {
				System.out.println(p.getFirstName() + myUser.getSearchName());
				if (p.getFirstName().toLowerCase().equals(myUser.getSearchName().toLowerCase())
						&& p.getLastName().toLowerCase().equals(myUser.getSearchSurname().toLowerCase())) {
					list.add(p);
				} else {
					System.out.println("Ne slazu se");
				}
			}
		}

		for (PharmacistDTO p : list) {
			System.out.println("ret list" + p.getFirstName());
		}
		if (list.isEmpty()) {

			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

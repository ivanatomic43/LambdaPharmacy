package com.example.pharmacybackend.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.PromotionDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.User;

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
	private UserServiceImpl userService;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/getAllPharmacies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPharmacies() {

		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		List<PharmacyDTO> retPha = new ArrayList<>();

		for (Pharmacy p : pharmacies) {

			PharmacyDTO newPha = new PharmacyDTO();
			newPha.setId(p.getId());
			newPha.setName(p.getName());
			newPha.setStreet(p.getAdd().getStreet());
			newPha.setCity(p.getAdd().getCity());
			newPha.setLatitude(p.getAdd().getLatitude());
			newPha.setLongitude(p.getAdd().getLongitude());
			newPha.setDescription(p.getDescription());
			newPha.setRating(p.getRating());

			retPha.add(newPha);

		}

		return new ResponseEntity<>(retPha, HttpStatus.OK);
	}

	@RequestMapping(value = "/createPharmacy", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> createPharmacy(@RequestBody PharmacyDTO pharmacy, HttpServletRequest request) {

		PharmacyDTO createdPharmacy = pharmacyService.createPharmacy(pharmacy);

		if (createdPharmacy == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(createdPharmacy, HttpStatus.OK);

	}

	@RequestMapping(value = "/getPharmacy/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPharmacy(@PathVariable("id") Long id) {

		Pharmacy p = this.pharmacyService.getPharmacy(id);

		PharmacyDTO newPha = new PharmacyDTO();
		newPha.setId(p.getId());
		newPha.setName(p.getName());
		newPha.setStreet(p.getAdd().getStreet());
		newPha.setCity(p.getAdd().getCity());
		newPha.setLatitude(p.getAdd().getLatitude());
		newPha.setLongitude(p.getAdd().getLongitude());
		newPha.setDescription(p.getDescription());
		newPha.setRating(p.getRating());

		if (newPha == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(newPha, HttpStatus.OK);

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
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> subscribeForNewsletter(@PathVariable("id") Long id, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean subscribed = pharmacyService.subscribeForNewsletter(id, user.getId());

		return new ResponseEntity<>(subscribed, HttpStatus.OK);

	}

	@RequestMapping(value = "/unsubscribe/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> unsubscribeForNewsletter(@PathVariable("id") Long id, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean unsubscribed = pharmacyService.unsubscribeForNewsletter(id, user.getId());

		return new ResponseEntity<>(unsubscribed, HttpStatus.OK);

	}

	@RequestMapping(value = "/getSubPharmacies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PATIENT')")
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
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> fetchAllPromotions(@PathVariable("id") Long id) {

		List<PromotionDTO> list = pharmacyService.fetchAllPromotions(id);

		if (list.isEmpty())
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/createPromotion/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> fetchAllPromotions(@PathVariable("id") Long id, @RequestBody PromotionDTO newProm) {

		PromotionDTO newPromotion = pharmacyService.createPromotion(id, newProm);

		if (newPromotion == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(newPromotion, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAdminsPharmacy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> getMyPharmacy(HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		PharmacyDTO myPharm = pharmacyService.getMyPharmacy(user.getId());

		return new ResponseEntity<>(myPharm, HttpStatus.OK);

	}

	@RequestMapping(value = "/getEmployedStaff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> getEmployedStaff(@PathVariable("id") Long pharmacyID) {

		List<DermatologistDTO> retList = pharmacyService.getEmployedStaff(pharmacyID);

		if (retList.isEmpty()) {
			return new ResponseEntity<>(retList, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(retList, HttpStatus.OK);

	}

}

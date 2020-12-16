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

import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.model.Image;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.services.ImageService;
import com.example.pharmacybackend.services.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
@CrossOrigin(origins = "http://localhost:4200")
public class PharmacyController {

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/getAllPharmacies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPharmacies() {

		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		List<PharmacyDTO> retPha = new ArrayList<>();

		for (Pharmacy p : pharmacies) {
			PharmacyDTO newPha = new PharmacyDTO(p.getId(), p.getAddress(), p.getDescription(), p.getName(),
					p.getRating());
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

}

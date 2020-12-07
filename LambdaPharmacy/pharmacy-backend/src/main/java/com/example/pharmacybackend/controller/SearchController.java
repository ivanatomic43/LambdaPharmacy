package com.example.pharmacybackend.controller;



import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.SimpleSearchDTO;
import com.example.pharmacybackend.services.MedicineService;
import com.example.pharmacybackend.services.PharmacyService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	
	@RequestMapping(value = "/searchMedicine/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchMedicine(@PathVariable("name") String name, HttpServletRequest httpRequest){
		
		List<MedicineDTO> medicines = medicineService.searchMedicine(name);
		
		if(medicines.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchPharmacy",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchPharmacy(@RequestBody SimpleSearchDTO p){
		
		System.out.println("SIMPLE SEARCHDTO" + p.toString());
		
		String name = "";
		String location= "";
		double rating = 0;
		
		if(p.getPharmacyName() != null) {
			name = p.getPharmacyName();
		}
		if(p.getPharmacyLocation() != null) {
			location = p.getPharmacyLocation();
		}
		if(p.getPharmacyRating() != 0) {
			rating = p.getPharmacyRating();
		}
		
		List<PharmacyDTO> pharmacies = pharmacyService.searchPharmacy(name, location, rating);
		
		if(pharmacies.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pharmacies, HttpStatus.OK);
	}
	
	
}

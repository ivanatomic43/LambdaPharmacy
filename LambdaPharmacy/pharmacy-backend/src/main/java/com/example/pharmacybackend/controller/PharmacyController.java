package com.example.pharmacybackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.services.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

	
	@Autowired
	private PharmacyService pharmacyService;
	
	 @RequestMapping(value = "/getAllPharmacies", method = RequestMethod.GET)
	    public ResponseEntity<?> getAllPharmacies() {
		 		
		 		List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
		 		List<PharmacyDTO> retPha = new ArrayList<>();
		 		
		 		for(Pharmacy p : pharmacies) {
		 			PharmacyDTO newPha = new PharmacyDTO(p.getId(), p.getAddress(), p.getDescription(), p.getName());
		 			retPha.add(newPha);
		 			
		 		}
		 		 
		 		 return new ResponseEntity<>(retPha, HttpStatus.OK);
}
	
	
}

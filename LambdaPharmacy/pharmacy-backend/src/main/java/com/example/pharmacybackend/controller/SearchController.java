package com.example.pharmacybackend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.services.MedicineService;
import com.example.pharmacybackend.services.PharmacyService;

import java.util.*;

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
	
	
}

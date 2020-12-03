package com.example.pharmacybackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.model.Medicine;
import com.example.pharmacybackend.services.MedicineService;

@RestController
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineController {

	
	@Autowired
	private MedicineService medicineService;
	
	 @RequestMapping(value = "/getAllMedicines", method = RequestMethod.GET)
	    public ResponseEntity<?> getAllMedicines() {
		 		System.out.println("USAO U GET ALL med");
		 		List<Medicine> medicines = medicineService.getAllMedicine();
		 		List<MedicineDTO> retMed = new ArrayList<>();
		 		
		 		for(Medicine m : medicines) {
		 			MedicineDTO newMed = new MedicineDTO(m.getId(), m.getMedicine_code(), m.getMed_type(), m.getName(), m.getShape(), m.getProducer(), m.getStructure(), m.getMode(), m.getNote());
		 			retMed.add(newMed);
		 			
		 		}
		 		 
		 		 return new ResponseEntity<>(retMed, HttpStatus.OK);
}
	
}

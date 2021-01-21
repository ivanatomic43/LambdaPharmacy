package com.example.pharmacybackend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.bootstrap.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.dto.PriceDTO;
import com.example.pharmacybackend.dto.ReservationParamsDTO;
import com.example.pharmacybackend.model.Medicine;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.security.TokenUtils;
import com.example.pharmacybackend.services.MedicineService;
import com.example.pharmacybackend.services.UserServiceImpl;

@RestController
@RequestMapping(value = "/medicine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	TokenUtils tokenUtils;

	@RequestMapping(value = "/getAllMedicines", method = RequestMethod.GET) // in all pharmacies
	public ResponseEntity<?> getAllMedicines() {

		List<MedicineDTO> medicines = medicineService.getAllMedicine();

		if (medicines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@RequestMapping(value = "/getPharmacyMedicines/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPharmacyMedicines(@PathVariable("id") Long id) {

		List<MedicineDTO> medicines = medicineService.getPharmacyMedicines(id);

		if (medicines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@RequestMapping(value = "/reserveMedicine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> reserveMedicine(@RequestBody ReservationParamsDTO params, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean reserved = medicineService.reserveMedicine(params, user.getId());

		return new ResponseEntity<>(reserved, HttpStatus.OK);

	}

	@RequestMapping(value = "/getPatientReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> getPatientReservations(HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		List<ReservationParamsDTO> myReservations = medicineService.getPatientReservations(user.getId());

		if (myReservations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(myReservations, HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllMedicinesInSystem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> getAllMedicinesInSystem() {

		List<MedicineDTO> allMedicines = medicineService.getAllMedicinesInSystem();

		if (allMedicines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(allMedicines, HttpStatus.OK);

	}

	@RequestMapping(value = "/registerMedicine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> registerMedicine(@RequestBody MedicineDTO newMedicine, HttpServletRequest request) {

		MedicineDTO myMed = medicineService.registerMedicine(newMedicine);

		return new ResponseEntity<>(myMed, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMedForAdd/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> getMedForAdd(@PathVariable("id") Long id) {

		List<MedicineDTO> list = medicineService.getMedForAdd(id);

		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/addMedicineToPharmacy/{id}/{medicineID}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> addMedicineToPharmacy(@PathVariable("id") Long id,
			@PathVariable("medicineID") Long medicineID) {

		boolean added = medicineService.addMedicineToPharmacy(id, medicineID);

		if (!added) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(added, HttpStatus.OK);
	}

	@RequestMapping(value = "/getNotReservedMedicines", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getNotReservedMedicines() {

		List<MedicineDTO> allMedicines = medicineService.getNotReservedMedicines();

		if (allMedicines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(allMedicines, HttpStatus.OK);

	}

	@RequestMapping(value = "/cancelMedicineReservation/{id}")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<?> cancelMedicineReservation(@PathVariable("id") Long id, HttpServletRequest request) {

		String myToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(myToken);
		User user = userService.findByUsername(username);

		boolean cancelled = medicineService.cancelMedicineReservation(id, user.getId());

		if (!cancelled) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getMedicineDetails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMedicineDetails(@PathVariable("id") Long id) {

		MedicineDTO myMed = medicineService.getMedicineDetails(id);

		return new ResponseEntity<>(myMed, HttpStatus.OK);

	}

	@RequestMapping(value = "/editPrice/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PHARMACY_ADMIN')")
	public ResponseEntity<?> editPrice(@PathVariable("id") Long id, @RequestBody PriceDTO model) {

		boolean changed = medicineService.editPrice(id, model);

		if (!changed) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(changed, HttpStatus.OK);
	}

}

package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.ReservationRepository;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.dto.ReservationParamsDTO;
import com.example.pharmacybackend.enumerations.MedicineStatus;
import com.example.pharmacybackend.model.*;
import java.util.*;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ReservationRepository reservationRepository;

	public List<MedicineDTO> getAllMedicine() {

		List<Pharmacy> allPharm = pharmacyRepository.findAll();

		List<Medicine> allMedicines = new ArrayList<>();
		List<MedicineDTO> myMed = new ArrayList<>();

		for (Pharmacy p : allPharm) {

			allMedicines = p.getListOfMedicines();

			for (Medicine m : allMedicines) {
				MedicineDTO dto = new MedicineDTO();
				dto.setId(m.getId());
				dto.setMedType(m.getMed_type());
				dto.setMedicineCode(m.getMedicine_code());
				dto.setModee(m.getMode().toString());
				dto.setName(m.getName());
				dto.setNote(m.getNote());
				dto.setPharmacyID(p.getId());
				dto.setPharmacyName(p.getName());
				dto.setProducer(m.getProducer());
				dto.setShape(m.getShape());
				dto.setStructure(m.getStructure());
				// dto.setStatus(m.getStatus().toString());

				myMed.add(dto);
			}

		}

		return myMed;
	}

	public List<MedicineDTO> searchMedicine(String med) {

		List<MedicineDTO> retMed = new ArrayList<>();
		List<Medicine> allMed = new ArrayList<>();

		List<Pharmacy> allPharm = pharmacyRepository.findAll();

		for (Pharmacy p : allPharm) {

			allMed = p.getListOfMedicines();

			for (Medicine m : allMed) {
				if (m.getName().toLowerCase().contains(med.toLowerCase())) {

					MedicineDTO dto = new MedicineDTO();
					dto.setId(m.getId());
					dto.setMedType(m.getMed_type());
					dto.setMedicineCode(m.getMedicine_code());
					dto.setModee(m.getMode().toString());
					dto.setName(m.getName());
					dto.setNote(m.getNote());
					dto.setPharmacyID(p.getId());
					dto.setPharmacyName(p.getName());
					dto.setProducer(m.getProducer());
					dto.setShape(m.getShape());
					dto.setStructure(m.getStructure());
					// dto.setStatus(m.getStatus().toString());

					retMed.add(dto);

				}
			}

		}

		return retMed;
	}

	public List<MedicineDTO> getPharmacyMedicines(Long id) {

		Pharmacy pharmacy = pharmacyRepository.findOneById(id);

		List<Medicine> medicines = pharmacy.getListOfMedicines();
		List<MedicineDTO> retList = new ArrayList<>();

		if (medicines.isEmpty()) {
			System.out.println("There is no medicines in pharmacy...");
			return null;
		}

		for (Medicine m : medicines) {

			MedicineDTO dto = new MedicineDTO();
			dto.setId(m.getId());
			dto.setMedType(m.getMed_type());
			dto.setMedicineCode(m.getMedicine_code());
			dto.setModee(m.getMode().toString());
			dto.setName(m.getName());
			dto.setNote(m.getNote());
			dto.setPharmacyID(pharmacy.getId());
			dto.setPharmacyName(pharmacy.getName());
			dto.setProducer(m.getProducer());
			dto.setShape(m.getShape());
			dto.setStructure(m.getStructure());
			// dto.setStatus(m.getStatus().toString());

			retList.add(dto);

		}

		return retList;
	}

	public boolean reserveMedicine(ReservationParamsDTO dto, Long id) {

		boolean reserved = false;
		Patient patient = patientRepository.findOneById(id);

		List<Pharmacy> allPharm = pharmacyRepository.findAll();

		for (Pharmacy p : allPharm) {
			if (p.getId() == dto.getPharmacyID()) {

				List<Medicine> allMed = p.getListOfMedicines();

				for (Medicine m : allMed) {
					if (m.getId() == dto.getMedicineID()) {

						MedicineReservation medRes = new MedicineReservation();
						medRes.setDate(dto.getDate());
						// m.setStatus(MedicineStatus.RESERVED);
						medRes.setMedicine(m);
						medRes.setPatient(patient);
						medRes.setPharmacy(p);

						reservationRepository.save(medRes);

						emailService.sendMedicineReservationMail(patient, medRes.getId());

						reserved = true;

					}
				}

			}

		}

		return reserved;
	}

	public List<ReservationParamsDTO> getPatientReservations(Long id) {

		List<MedicineReservation> myRes = reservationRepository.findByPatientId(id);

		List<ReservationParamsDTO> retList = new ArrayList<>();

		if (!myRes.isEmpty()) {
			for (MedicineReservation m : myRes) {

				ReservationParamsDTO dto = new ReservationParamsDTO();
				dto.setId(m.getId());
				dto.setDatee(m.getDate().toString());
				dto.setMedicineID(m.getMedicine().getId());
				dto.setMedicineName(m.getMedicine().getName());
				dto.setPharmacyID(m.getPharmacy().getId());
				dto.setPharmacyName(m.getPharmacy().getName());
				dto.setStatus(MedicineStatus.RESERVED.toString());

				retList.add(dto);
			}

		}

		return retList;
	}

	public List<MedicineDTO> getAllMedicinesInSystem() {

		List<Medicine> allMed = medicineRepository.findAll();
		List<MedicineDTO> retList = new ArrayList<>();

		for (Medicine m : allMed) {
			MedicineDTO dto = new MedicineDTO();
			dto.setId(m.getId());
			dto.setMedType(m.getMed_type());
			dto.setMedicineCode(m.getMedicine_code());
			dto.setModee(m.getMode().toString());
			dto.setName(m.getName());
			dto.setNote(m.getNote());
			dto.setProducer(m.getProducer());
			dto.setShape(m.getShape());
			dto.setStructure(m.getStructure());
			dto.setContraindications(m.getContraindications());
			dto.setDailyDose(m.getDailyDose());

			retList.add(dto);
		}
		return retList;
	}

	public MedicineDTO registerMedicine(MedicineDTO newMedicine) {

		Medicine dto = new Medicine();

		List<Medicine> allMed = medicineRepository.findAll();

		for (Medicine m : allMed) {
			if (m.getMedicine_code().equals(newMedicine.getMedicineCode())) {
				System.out.println("Medicine with that code already exists...");
				return null;
			}
		}

		dto.setName(newMedicine.getName());
		dto.setMedicine_code(newMedicine.getMedicineCode());
		dto.setMed_type(newMedicine.getMedType());
		dto.setShape(newMedicine.getShape());
		dto.setStructure(newMedicine.getStructure());
		dto.setContraindications(newMedicine.getContraindications());
		dto.setDailyDose(newMedicine.getDailyDose());
		dto.setMode(newMedicine.getMode());
		dto.setNote(newMedicine.getNote());
		dto.setProducer(newMedicine.getProducer());

		medicineRepository.save(dto);

		MedicineDTO ret = new MedicineDTO();

		ret.setId(dto.getId());
		ret.setName(dto.getName());
		ret.setMedicineCode(dto.getMedicine_code());
		ret.setMedType(dto.getMed_type());
		ret.setShape(dto.getShape());
		ret.setStructure(dto.getStructure());
		ret.setContraindications(dto.getContraindications());
		ret.setDailyDose(dto.getDailyDose());
		ret.setModee(dto.getMode().toString());
		ret.setNote(dto.getNote());
		ret.setProducer(dto.getProducer());

		return ret;

	}

}

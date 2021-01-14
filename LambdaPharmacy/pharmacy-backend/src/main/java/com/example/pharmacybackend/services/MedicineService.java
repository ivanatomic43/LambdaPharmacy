package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacyMedicinesRepository;
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
	private PharmacyMedicinesRepository pharmacyMedicinesRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ReservationRepository reservationRepository;

	public List<MedicineDTO> getAllMedicine() {

		List<PharmacyMedicine> medicinesInPharmacies = pharmacyMedicinesRepository.findAll();
		List<MedicineDTO> myMed = new ArrayList<>();

		for (PharmacyMedicine m : medicinesInPharmacies) {

			MedicineDTO dto = new MedicineDTO();
			dto.setId(m.getId());
			dto.setMedType(m.getMedicine().getMed_type());
			dto.setMedicineCode(m.getMedicine().getMedicine_code());
			dto.setModee(m.getMedicine().getMode().toString());
			dto.setName(m.getMedicine().getName());
			dto.setNote(m.getMedicine().getNote());
			dto.setPharmacyID(m.getPharmacy().getId());
			dto.setPharmacyName(m.getPharmacy().getName());
			dto.setProducer(m.getMedicine().getProducer());
			dto.setShape(m.getMedicine().getShape());
			dto.setStructure(m.getMedicine().getStructure());
			dto.setStatus(m.getStatusInPharmacy().toString());
			dto.setQuantity(m.getQuantity());

			myMed.add(dto);
		}

		return myMed;
	}

	public List<MedicineDTO> searchMedicine(String med) {

		List<MedicineDTO> retMed = new ArrayList<>();
		List<PharmacyMedicine> allMeds = pharmacyMedicinesRepository.findAll();

		for (PharmacyMedicine m : allMeds) {
			if (m.getMedicine().getName().toLowerCase().contains(med.toLowerCase())) {

				MedicineDTO dto = new MedicineDTO();
				dto.setId(m.getMedicine().getId());
				dto.setMedType(m.getMedicine().getMed_type());
				dto.setMedicineCode(m.getMedicine().getMedicine_code());
				dto.setModee(m.getMedicine().getMode().toString());
				dto.setName(m.getMedicine().getName());
				dto.setNote(m.getMedicine().getNote());
				dto.setPharmacyID(m.getPharmacy().getId());
				dto.setPharmacyName(m.getPharmacy().getName());
				dto.setProducer(m.getMedicine().getProducer());
				dto.setShape(m.getMedicine().getShape());
				dto.setStructure(m.getMedicine().getStructure());
				dto.setStatus(m.getStatusInPharmacy().toString());
				dto.setQuantity(m.getQuantity());

				retMed.add(dto);

			}
		}

		return retMed;
	}

	public List<MedicineDTO> getPharmacyMedicines(Long id) {

		List<MedicineDTO> retList = new ArrayList<>();

		List<PharmacyMedicine> medicinesInPharmacy = pharmacyMedicinesRepository.findAll();

		for (PharmacyMedicine m : medicinesInPharmacy) {

			if (m.getPharmacy().getId() == id) {

				MedicineDTO dto = new MedicineDTO();
				dto.setId(m.getId());
				dto.setMedType(m.getMedicine().getMed_type());
				dto.setMedicineCode(m.getMedicine().getMedicine_code());
				dto.setModee(m.getMedicine().getMode().toString());
				dto.setName(m.getMedicine().getName());
				dto.setNote(m.getMedicine().getNote());
				dto.setPharmacyID(m.getPharmacy().getId());
				dto.setPharmacyName(m.getPharmacy().getName());
				dto.setProducer(m.getMedicine().getProducer());
				dto.setShape(m.getMedicine().getShape());
				dto.setStructure(m.getMedicine().getStructure());

				if (m.getQuantity() == 0) {
					dto.setStatus(MedicineStatus.OUT_OF_STOCK.toString());
				} else {

					dto.setStatus(m.getStatusInPharmacy().toString());
				}
				dto.setQuantity(m.getQuantity());
				retList.add(dto);

			}

		}

		return retList;
	}

	public boolean reserveMedicine(ReservationParamsDTO dto, Long patientID) {

		boolean reserved = false;
		Patient patient = patientRepository.findOneById(patientID);

		List<PharmacyMedicine> medicinesInPharmacy = pharmacyMedicinesRepository.findAll();

		for (PharmacyMedicine m : medicinesInPharmacy) {
			if (m.getPharmacy().getId() == dto.getPharmacyID() && m.getId() == dto.getMedicineID()) {

				MedicineReservation medRes = new MedicineReservation();
				medRes.setDate(dto.getDate());

				medRes.setMedicine(m.getMedicine());
				medRes.setPatient(patient);
				medRes.setPharmacy(m.getPharmacy());

				if (m.getQuantity() != 0) {
					double newMedQuantity = m.getQuantity() - 1;
					m.setQuantity(newMedQuantity);
					if (m.getQuantity() == 0) {
						m.setStatusInPharmacy(MedicineStatus.OUT_OF_STOCK);
					} else {
						m.setStatusInPharmacy(MedicineStatus.AVAILABLE);
					}
				} else {
					m.setStatusInPharmacy(MedicineStatus.OUT_OF_STOCK);
				}
				pharmacyMedicinesRepository.save(m);
				reservationRepository.save(medRes);

				emailService.sendMedicineReservationMail(patient, medRes.getId());

				reserved = true;

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
				dto.setQuantity(1);

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
		dto.setDefaultStatus(MedicineStatus.AVAILABLE);

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
		ret.setStatus(dto.getDefaultStatus().toString());

		return ret;

	}

	public List<MedicineDTO> getMedForAdd(Long id) {

		List<MedicineDTO> retList = new ArrayList<>();

		Pharmacy pharmacy = pharmacyRepository.findOneById(id);

		List<Medicine> allMed = medicineRepository.findAll();

		List<Medicine> pharmMed = pharmacy.getListOfMedicines();

		for (Medicine m : allMed) {

			if (!pharmMed.contains(m)) {

				MedicineDTO dto = new MedicineDTO();
				dto.setId(m.getId());
				dto.setName(m.getName());
				dto.setContraindications(m.getContraindications());
				dto.setDailyDose(m.getDailyDose());
				dto.setMedType(m.getMed_type());
				dto.setMedicineCode(m.getMedicine_code());
				dto.setModee(m.getMode().toString());
				dto.setNote(m.getNote());
				dto.setPharmacyID(id);
				dto.setPharmacyName(pharmacy.getName());
				dto.setProducer(m.getProducer());
				dto.setStatus("AVAILABLE");
				dto.setShape(m.getShape());
				dto.setStructure(m.getStructure());

				retList.add(dto);
			}
		}

		return retList;

	}

	public boolean addMedicineToPharmacy(Long pharmacyID, Long medicineID) {

		boolean added = false;

		Pharmacy pharmacy = pharmacyRepository.findOneById(pharmacyID);
		Medicine medicine = medicineRepository.findOneById(medicineID);

		List<Medicine> allPharmMed = pharmacy.getListOfMedicines();

		System.out.println(pharmacyID);
		System.out.println("Medicine id" + medicineID);
		System.out.println("Pronadjen lek " + medicine.getId());

		if (!allPharmMed.contains(medicine)) {
			allPharmMed.add(medicine);
			pharmacyRepository.save(pharmacy);
			added = true;
		}

		return added;

	}

	public List<MedicineDTO> getNotReservedMedicines() {

		List<PharmacyMedicine> medicinesInPharmacies = pharmacyMedicinesRepository.findAll();
		List<MedicineDTO> myMed = new ArrayList<>();

		for (PharmacyMedicine m : medicinesInPharmacies) {
			if (!m.getStatusInPharmacy().equals(MedicineStatus.OUT_OF_STOCK)) {
				MedicineDTO dto = new MedicineDTO();
				dto.setId(m.getId());
				dto.setMedType(m.getMedicine().getMed_type());
				dto.setMedicineCode(m.getMedicine().getMedicine_code());
				dto.setModee(m.getMedicine().getMode().toString());
				dto.setName(m.getMedicine().getName());
				dto.setNote(m.getMedicine().getNote());
				dto.setPharmacyID(m.getPharmacy().getId());
				dto.setPharmacyName(m.getPharmacy().getName());
				dto.setProducer(m.getMedicine().getProducer());
				dto.setShape(m.getMedicine().getShape());
				dto.setStructure(m.getMedicine().getStructure());
				dto.setStatus(m.getStatusInPharmacy().toString());
				dto.setQuantity(m.getQuantity());

				myMed.add(dto);
			} else {
				System.out.println("Lek je rezervisan");
			}
		}

		return myMed;

	}

	public boolean cancelMedicineReservation(Long resID, Long patientID) {

		boolean cancelled = false;

		MedicineReservation medRes = reservationRepository.findOneById(resID);
		Patient patient = patientRepository.findOneById(patientID);

		if (medRes.getPatient().getId() == patient.getId()) {

			Calendar myDate = Calendar.getInstance();
			Date currentDate = Calendar.getInstance().getTime();
			Date dateOfReservation = medRes.getDate();

			myDate.setTime(dateOfReservation);
			myDate.add(Calendar.HOUR_OF_DAY, -24);
			System.out.println("CURRENT DATE" + currentDate);
			System.out.println("MY DATE -24" + myDate);

			Date lastDayToCancel = myDate.getTime();

			if (currentDate.before(lastDayToCancel)) {

				// vrati kolicinu u +1
				// izvuci sve lekove u apotekama
				List<PharmacyMedicine> allMedsInPharmacy = pharmacyMedicinesRepository.findAll();

				for (PharmacyMedicine pm : allMedsInPharmacy) {
					if (medRes != null) {
						if (medRes.getMedicine().getId() == pm.getMedicine().getId()
								&& medRes.getPharmacy().getId() == pm.getPharmacy().getId()) {

							double newQuantity = pm.getQuantity() + 1;
							pm.setQuantity(newQuantity);
							System.out.println(pm.getQuantity());
							medRes.setPatient(null);
							medRes.setPharmacy(null);
							medRes.setMedicine(null);
							reservationRepository.delete(medRes);
							pharmacyMedicinesRepository.save(pm);
							cancelled = true;
							return cancelled;
						}
					}

				}

			} else {
				System.out.println("Date error");
			}

		}

		return cancelled;
	}

}

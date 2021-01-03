package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.model.*;
import java.util.*;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PharmacyRepository pharmacyRepository;

	public List<Medicine> getAllMedicine() {
		return medicineRepository.findAll();
	}

	public List<MedicineDTO> searchMedicine(String med) {
		System.out.println("IME" + med);
		List<MedicineDTO> retMed = new ArrayList<>();

		List<Medicine> medicines = medicineRepository.findAll();

		for (Medicine m : medicines) {
			if (med.toLowerCase().equals(m.getName().toLowerCase())) {
				MedicineDTO newMed = new MedicineDTO(m.getId(), m.getMedicine_code(), m.getMed_type(), m.getName(),
						m.getShape(), m.getProducer(), m.getStructure(), m.getMode(), m.getNote());
				retMed.add(newMed);
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

			retList.add(dto);

		}

		return retList;
	}
}

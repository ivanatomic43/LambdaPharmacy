package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.dto.MedicineDTO;
import com.example.pharmacybackend.model.*;
import java.util.*;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	
	
	public List<Medicine> getAllMedicine(){
		return medicineRepository.findAll();
	}
	
	public List<MedicineDTO> searchMedicine(String med){
		System.out.println("IME" + med);
		List<MedicineDTO> retMed = new ArrayList<>();
		
		List<Medicine> medicines = medicineRepository.findAll();
		
		for(Medicine m : medicines) {
			if(med.toLowerCase().equals(m.getName().toLowerCase())) {
				MedicineDTO newMed = new MedicineDTO(m.getId(), m.getMedicine_code(), m.getMed_type(), m.getName(), 
						m.getShape(), m.getProducer(),m.getStructure(), m.getMode(), m.getNote());
				retMed.add(newMed);
			}
		}
		
		return retMed;
	}
}

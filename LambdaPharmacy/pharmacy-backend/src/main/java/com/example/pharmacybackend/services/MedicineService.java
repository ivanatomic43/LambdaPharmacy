package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.MedicineRepository;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.model.*;
import java.util.*;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	
	
	public List<Medicine> getAllMedicine(){
		return medicineRepository.findAll();
	}
	
}

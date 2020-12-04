package com.example.pharmacybackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.model.Pharmacy;

@Service
public class PharmacyService {

	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	public List<Pharmacy> getAllPharmacies(){
		return pharmacyRepository.findAll();
	}
	
}

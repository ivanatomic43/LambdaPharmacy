package com.example.pharmacybackend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.model.Pharmacy;

@Service
public class PharmacyService {

	
	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	public List<Pharmacy> getAllPharmacies(){
		return pharmacyRepository.findAll();
	}
	
	public List<PharmacyDTO> searchPharmacy(String name, String location, double rating){
		System.out.println("ime" + name + "lokacija" + location + "rating" + rating);
		List<PharmacyDTO> retPha = new ArrayList<>();
		List<Pharmacy> pha= new ArrayList<>();
		List<Pharmacy> list = pharmacyRepository.findAll();
		
		for(Pharmacy p : list) {
			System.out.println(p.getName());
			if(p.getName().toLowerCase().equals(name.toLowerCase())) {
				System.out.println("usao u name" + p.getName());
				pha.add(p);
				PharmacyDTO dto = new PharmacyDTO(p);
				retPha.add(dto);
			}
		}
		
		
	
		
		return retPha;
	}
}
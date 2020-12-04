package com.example.pharmacybackend.dto;

import com.example.pharmacybackend.model.Pharmacy;

public class PharmacyDTO {

	
	private Long id;
	private String address;
	private String description;
	private String name;
	
	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getAddress(), p.getDescription(), p.getName());
	}
	
	public PharmacyDTO(Long id, String address, String description, String name) {
		this.id = id;
		this.address= address;
		this.description= description;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

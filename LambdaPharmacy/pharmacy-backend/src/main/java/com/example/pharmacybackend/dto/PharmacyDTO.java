package com.example.pharmacybackend.dto;

import com.example.pharmacybackend.model.Pharmacy;

public class PharmacyDTO {

	private Long id;
	private String address;
	private String description;
	private String name;
	private double rating;

	public PharmacyDTO() {
	}

	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getAddress(), p.getDescription(), p.getName(), p.getRating());
	}

	public PharmacyDTO(Long id, String address, String description, String name, double rating) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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

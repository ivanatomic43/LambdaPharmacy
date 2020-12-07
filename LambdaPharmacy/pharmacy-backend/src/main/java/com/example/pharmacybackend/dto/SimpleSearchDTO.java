package com.example.pharmacybackend.dto;

public class SimpleSearchDTO {

	
	private String pharmacyName;
	private String pharmacyLocation;
	private double pharmacyRating;
	
	public SimpleSearchDTO() {}
	
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getPharmacyLocation() {
		return pharmacyLocation;
	}
	public void setPharmacyLocation(String pharmacyLocation) {
		this.pharmacyLocation = pharmacyLocation;
	}

	public double getPharmacyRating() {
		return pharmacyRating;
	}

	public void setPharmacyRating(double pharmacyRating) {
		this.pharmacyRating = pharmacyRating;
	}

	
	
	
	
}

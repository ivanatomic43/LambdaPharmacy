package com.example.pharmacybackend.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("USER_PHARMA_ADMIN")
public class PharmacyAdministrator extends User{

	
	@ManyToOne
	private Pharmacy pharmacy;
	
}

package com.example.pharmacybackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("USER_PHARMA_ADMIN")
@Table(name = "PHARMACY_ADMINISTRATOR")
public class PharmacyAdministrator extends User {

	@ManyToOne
	private Pharmacy pharmacy;

	/**
	 * @return Pharmacy return the pharmacy
	 */
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	/**
	 * @param pharmacy the pharmacy to set
	 */
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

}

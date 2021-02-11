package com.example.pharmacybackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.*;

@Entity
@DiscriminatorValue("USER_PHARMA_ADMIN")
public class PharmacyAdministrator extends User {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pharmacy_admin_id")
	private Pharmacy pharmacy;

	@OneToMany(mappedBy = "admin")
	private List<PurchaseOrder> myOrderList;

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

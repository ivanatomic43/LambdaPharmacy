package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PATIENT")
public class Patient extends User {

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "patient_subscribed_pharmacy", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
	private List<Pharmacy> subscribedPharmacies;

	/**
	 * @return List<Pharmacy> return the subscribedPharmacies
	 */
	public List<Pharmacy> getSubscribedPharmacies() {
		return subscribedPharmacies;
	}

	/**
	 * @param subscribedPharmacies the subscribedPharmacies to set
	 */
	public void setSubscribedPharmacies(List<Pharmacy> subscribedPharmacies) {
		this.subscribedPharmacies = subscribedPharmacies;
	}

}

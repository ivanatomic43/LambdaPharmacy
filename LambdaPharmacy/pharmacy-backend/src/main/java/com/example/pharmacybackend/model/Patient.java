package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PATIENT")
public class Patient extends User {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments = new ArrayList<>();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Recipe> recipies = new ArrayList<>();

	/**
	 * @return List<Appointment> return the appointments
	 */
	public List<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * @return List<Recipe> return the recipies
	 */
	public List<Recipe> getRecipies() {
		return recipies;
	}

	/**
	 * @param recipies the recipies to set
	 */
	public void setRecipies(List<Recipe> recipies) {
		this.recipies = recipies;
	}

}

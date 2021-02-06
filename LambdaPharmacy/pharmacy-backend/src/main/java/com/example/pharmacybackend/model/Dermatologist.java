package com.example.pharmacybackend.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_DERMATOLOGIST")
public class Dermatologist extends User {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "dermatologist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> reservedAppointments = new ArrayList<>();

	@Column
	private double rating;

	public Dermatologist() {

	}

	/**
	 * @return List<Appointment> return the reservedAppointments
	 */
	public List<Appointment> getReservedAppointments() {
		return reservedAppointments;
	}

	/**
	 * @param reservedAppointments the reservedAppointments to set
	 */
	public void setReservedAppointments(List<Appointment> reservedAppointments) {
		this.reservedAppointments = reservedAppointments;
	}

	/**
	 * @return double return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

}

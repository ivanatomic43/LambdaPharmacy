package com.example.pharmacybackend.model;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_DERMATOLOGIST")
public class Dermatologist extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "dermatologist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> reservedAppointments = new ArrayList<>();

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

}

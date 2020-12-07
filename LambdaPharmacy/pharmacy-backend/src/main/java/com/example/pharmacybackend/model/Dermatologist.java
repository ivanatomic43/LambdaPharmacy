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

}

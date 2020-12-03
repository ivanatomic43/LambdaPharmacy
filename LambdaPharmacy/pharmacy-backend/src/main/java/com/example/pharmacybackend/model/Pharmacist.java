package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PHARMACIST")
public class Pharmacist extends User{

	
	@ManyToOne
	private Pharmacy pharmacy;
	
	
	@OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> reservedAppointments= new ArrayList<>();
}

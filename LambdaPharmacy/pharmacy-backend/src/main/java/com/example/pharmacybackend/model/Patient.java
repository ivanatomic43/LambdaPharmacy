package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PATIENT")
public class Patient extends User {

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments  = new ArrayList<>();
	
	@OneToMany(mappedBy="patient", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<Recipe> recipies = new ArrayList<>();
	
}

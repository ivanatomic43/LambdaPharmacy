package com.example.pharmacybackend.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PHARMACIST")
public class Pharmacist extends User {

	@ManyToOne
	private Pharmacy pharmacy;

	@OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> reservedAppointments = new ArrayList<>();

	@Column(name = "dateFrom")
	private Date dateFrom;

	@Column(name = "dateTo")
	private Date dateTo;

	@Column(name = "workingFrom")
	private LocalTime workingFrom;

	@Column(name = "workingTo")
	private LocalTime workingTo;

}

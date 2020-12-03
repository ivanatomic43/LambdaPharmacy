package com.example.pharmacybackend.model;

import java.util.Date;

import javax.persistence.*;
import com.example.pharmacybackend.enumerations.*;

@Entity
@Table(name="APPOINTMENT")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(nullable= false)
	private AppointmentType type;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private Date dateOfAppointment;
	
	@Column
	private double duration;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Dermatologist dermatologist;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pharmacist pharmacist;
	
	
}

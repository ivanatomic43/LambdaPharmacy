package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.MedicineMode;

@Entity
@Table(name="MEDICINE")
public class Medicine {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "medicine_code", nullable = false)
	private String medicine_code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String shape;
	
	@Column(nullable = false)
	private String producer;
	
	@Column(nullable = false)
	private MedicineMode mode;
	

	//@Column
	//private List<String> substituteMedicines = new ArrayList<>();
	
	@Column
	private String note;
}

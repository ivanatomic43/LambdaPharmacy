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
	private String med_type;
	
	@Column(nullable = false)
	private String shape;
	
	@Column(nullable = false)
	private String structure;
	
	@Column(nullable = false)
	private String producer;
	
	@Enumerated(EnumType.STRING)
	private MedicineMode mode;
	

	//@Column
	//private List<String> substituteMedicines = new ArrayList<>();
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMedicine_code() {
		return medicine_code;
	}


	public void setMedicine_code(String medicine_code) {
		this.medicine_code = medicine_code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMed_type() {
		return med_type;
	}


	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}


	public String getShape() {
		return shape;
	}


	public void setShape(String shape) {
		this.shape = shape;
	}


	public String getStructure() {
		return structure;
	}


	public void setStructure(String structure) {
		this.structure = structure;
	}


	public String getProducer() {
		return producer;
	}


	public void setProducer(String producer) {
		this.producer = producer;
	}


	public MedicineMode getMode() {
		return mode;
	}


	public void setMode(MedicineMode mode) {
		this.mode = mode;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	@Column
	private String note;
}

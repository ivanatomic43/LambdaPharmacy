package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "RECIPE")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

	@Column
	private Date date;

	@Version
	private int version;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipe_medicine", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
	private List<Medicine> listOfMedicines = new ArrayList<>();

	/**
	 * @return Long return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Patient return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return Date return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return int return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return List<Medicine> return the listOfMedicines
	 */
	public List<Medicine> getListOfMedicines() {
		return listOfMedicines;
	}

	/**
	 * @param listOfMedicines the listOfMedicines to set
	 */
	public void setListOfMedicines(List<Medicine> listOfMedicines) {
		this.listOfMedicines = listOfMedicines;
	}

}

package com.example.pharmacybackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "PHARMACY")
public class Pharmacy implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private double rating = 0;

	// @Column(nullable = false)
	// private List<Date> freeDates;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Pharmacy() {
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pharmacy_dermatologist", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
	private List<Dermatologist> dermatologists;

	@OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pharmacist> pharmacists = new ArrayList<>();

	@OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PharmacyAdministrator> pharmacyAdministrators = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pharmacy_medicine", joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
	private List<Medicine> listOfMedicines;

	@OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> pharmacyAppointments = new ArrayList<>();

	@ManyToOne
	private Pricelist pricelist;

	public List<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(List<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public List<PharmacyAdministrator> getPharmacyAdministrators() {
		return pharmacyAdministrators;
	}

	public void setPharmacyAdministrators(List<PharmacyAdministrator> pharmacyAdministrators) {
		this.pharmacyAdministrators = pharmacyAdministrators;
	}

	public List<Medicine> getListOfMedicines() {
		return listOfMedicines;
	}

	public void setListOfMedicines(List<Medicine> listOfMedicines) {
		this.listOfMedicines = listOfMedicines;
	}

	/*
	 * public Image getImage() { return image; }
	 * 
	 * public void setImage(Image image) { this.image = image; }
	 */
	@OneToOne(mappedBy = "pharmacy", fetch = FetchType.LAZY)
	private Image image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public List<Date> getFreeDates() { return freeDates; }
	 * 
	 * 
	 * public void setFreeDates(List<Date> freeDates) { this.freeDates = freeDates;
	 * }
	 * 
	 * 
	 * public List<Dermatologist> getDermatologists() { return dermatologists; }
	 * 
	 * 
	 * public void setDermatologists(List<Dermatologist> dermatologists) {
	 * this.dermatologists = dermatologists; }
	 * 
	 */
	public List<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	/*
	 * public List<Medicine> getListOfMedicines() { return listOfMedicines; }
	 * 
	 * 
	 * public void setListOfMedicines(List<Medicine> listOfMedicines) {
	 * this.listOfMedicines = listOfMedicines; }
	 */

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	/**
	 * @return List<Appointment> return the pharmacyAppointments
	 */
	public List<Appointment> getPharmacyAppointments() {
		return pharmacyAppointments;
	}

	/**
	 * @param pharmacyAppointments the pharmacyAppointments to set
	 */
	public void setPharmacyAppointments(List<Appointment> pharmacyAppointments) {
		this.pharmacyAppointments = pharmacyAppointments;
	}

}

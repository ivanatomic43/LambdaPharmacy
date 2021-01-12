package com.example.pharmacybackend.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PHARMACIST")
public class Pharmacist extends User {

	private static final long serialVersionUID = 1L;

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

	@Column(name = "rating")
	private double rating;

	@Column(name = "price")
	private double price;

	/**
	 * @return Pharmacy return the pharmacy
	 */
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	/**
	 * @param pharmacy the pharmacy to set
	 */
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
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

	/**
	 * @return Date return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return Date return the dateTo
	 */
	public Date getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return LocalTime return the workingFrom
	 */
	public LocalTime getWorkingFrom() {
		return workingFrom;
	}

	/**
	 * @param workingFrom the workingFrom to set
	 */
	public void setWorkingFrom(LocalTime workingFrom) {
		this.workingFrom = workingFrom;
	}

	/**
	 * @return LocalTime return the workingTo
	 */
	public LocalTime getWorkingTo() {
		return workingTo;
	}

	/**
	 * @param workingTo the workingTo to set
	 */
	public void setWorkingTo(LocalTime workingTo) {
		this.workingTo = workingTo;
	}

	/**
	 * @return double return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return double return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}

package com.example.pharmacybackend.model;

import java.util.Date;
import java.time.*;

import javax.persistence.*;
import com.example.pharmacybackend.enumerations.*;

@Entity
@Table(name = "APPOINTMENT")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Enumerated(EnumType.STRING)
	private AppointmentType type;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private Date dateOfAppointment;

	@Column
	private long duration;

	@Column
	private LocalTime meetingTime;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private EmployedDermatologist dermatologist;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pharmacist pharmacist;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pharmacy pharmacy;

	@Version
	private int version;

	@Column
	private int loyaltyPoints;

	public Appointment(Long id, AppointmentType type, double price, Date date, long duration, LocalTime time,
			AppointmentStatus status, EmployedDermatologist d, Patient p, Pharmacy ph) {
		this.id = id;
		this.type = type;
		this.price = price;
		this.dateOfAppointment = date;
		this.duration = duration;
		this.meetingTime = time;
		this.status = status;
		this.dermatologist = d;
		this.patient = p;
		this.pharmacy = ph;
	}

	public Appointment() {
	}

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
	 * @return AppointmentType return the type
	 */
	public AppointmentType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AppointmentType type) {
		this.type = type;
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

	/**
	 * @return Date return the dateOfAppointment
	 */
	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}

	/**
	 * @param dateOfAppointment the dateOfAppointment to set
	 */
	public void setDateOfAppointment(Date dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	/**
	 * @return double return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return AppointmentStatus return the status
	 */
	public AppointmentStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppointmentStatus status) {
		this.status = status;
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
	 * @return Dermatologist return the dermatologist
	 */
	public EmployedDermatologist getDermatologist() {
		return dermatologist;
	}

	/**
	 * @param dermatologist the dermatologist to set
	 */
	public void setDermatologist(EmployedDermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	/**
	 * @return Pharmacist return the pharmacist
	 */
	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	/**
	 * @param pharmacist the pharmacist to set
	 */
	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	/**
	 * @return LocalTime return the meetingTime
	 */
	public LocalTime getMeetingTime() {
		return meetingTime;
	}

	/**
	 * @param meetingTime the meetingTime to set
	 */
	public void setMeetingTime(LocalTime meetingTime) {
		this.meetingTime = meetingTime;
	}

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
	 * @return int return the loyaltyPoints
	 */
	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	/**
	 * @param loyaltyPoints the loyaltyPoints to set
	 */
	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

}

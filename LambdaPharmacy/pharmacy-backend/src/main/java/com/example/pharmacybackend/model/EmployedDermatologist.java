package com.example.pharmacybackend.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "employed_dermatologist")
public class EmployedDermatologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    private Dermatologist dermatologist;

    @OneToOne
    private Pharmacy pharmacy;

    @Column(nullable = false)
    private Date dateFrom;

    @Column(nullable = false)
    private Date dateTo;

    @Column(nullable = false)
    private LocalTime workFrom;

    @Column(nullable = false)
    private LocalTime workTo;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "dermatologist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> reservedAppointments = new ArrayList<>();

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
     * @return Dermatologist return the dermatologist
     */
    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    /**
     * @param dermatologist the dermatologist to set
     */
    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
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
     * @return LocalTime return the workFrom
     */
    public LocalTime getWorkFrom() {
        return workFrom;
    }

    /**
     * @param workFrom the workFrom to set
     */
    public void setWorkFrom(LocalTime workFrom) {
        this.workFrom = workFrom;
    }

    /**
     * @return LocalTime return the workTo
     */
    public LocalTime getWorkTo() {
        return workTo;
    }

    /**
     * @param workTo the workTo to set
     */
    public void setWorkTo(LocalTime workTo) {
        this.workTo = workTo;
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

}

package com.example.pharmacybackend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.VacationStatus;

@Entity
@Table(name = "vacation")
public class Vacation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date vacationFrom;

    @Column
    private Date vacationTo;

    @ManyToOne
    private EmployedDermatologist dermatologist;

    @ManyToOne
    private Pharmacist pharmacist;

    @Enumerated(EnumType.STRING)
    private VacationStatus status;

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
     * @return Date return the vacationFrom
     */
    public Date getVacationFrom() {
        return vacationFrom;
    }

    /**
     * @param vacationFrom the vacationFrom to set
     */
    public void setVacationFrom(Date vacationFrom) {
        this.vacationFrom = vacationFrom;
    }

    /**
     * @return Date return the vacationTo
     */
    public Date getVacationTo() {
        return vacationTo;
    }

    /**
     * @param vacationTo the vacationTo to set
     */
    public void setVacationTo(Date vacationTo) {
        this.vacationTo = vacationTo;
    }

    /**
     * @return EmployedDermatologist return the dermatologist
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
     * @return VacationStatus return the status
     */
    public VacationStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(VacationStatus status) {
        this.status = status;
    }

}

package com.example.pharmacybackend.model;

import javax.persistence.*;
import javax.persistence.Table;

import com.example.pharmacybackend.enumerations.ComplaintStatus;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String text;

    @ManyToOne
    private Patient patient;

    @Version
    private int version;

    /*
     * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) private
     * Dermatologist dermatologist;
     * 
     * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) private
     * Pharmacist pharmacist;
     * 
     * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) private
     * Pharmacy pharmacy;
     */
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

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
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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
     * @return ComplaintStatus return the status
     */
    public ComplaintStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ComplaintStatus status) {
        this.status = status;
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

}

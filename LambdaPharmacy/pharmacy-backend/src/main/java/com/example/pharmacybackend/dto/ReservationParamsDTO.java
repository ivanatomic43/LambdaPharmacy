package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;

public class ReservationParamsDTO {

    private Long id;

    private Long medicineID;
    private Long pharmacyID;
    private Date date;

    private String medicineName;
    private String pharmacyName;
    private String datee;
    private String status;
    private double quantity;

    public ReservationParamsDTO() {
    }

    public ReservationParamsDTO(Long medicineID, Long pharmacyID, Date date) {
        this.medicineID = medicineID;
        this.pharmacyID = pharmacyID;
        this.date = date;

    }

    public ReservationParamsDTO(Long id, Long medicineID, Long pharmacyID, String datee, String medicineName,
            String pharmacyName, String status, double quantity) {
        this.id = id;
        this.medicineID = medicineID;
        this.pharmacyID = pharmacyID;
        this.datee = datee;
        this.medicineName = medicineName;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.quantity = quantity;

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
     * @return Long return the medicineID
     */
    public Long getMedicineID() {
        return medicineID;
    }

    /**
     * @param medicineID the medicineID to set
     */
    public void setMedicineID(Long medicineID) {
        this.medicineID = medicineID;
    }

    /**
     * @return Long return the pharmacyID
     */
    public Long getPharmacyID() {
        return pharmacyID;
    }

    /**
     * @param pharmacyID the pharmacyID to set
     */
    public void setPharmacyID(Long pharmacyID) {
        this.pharmacyID = pharmacyID;
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
     * @return String return the medicineName
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * @param medicineName the medicineName to set
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * @return String return the pharmacyName
     */
    public String getPharmacyName() {
        return pharmacyName;
    }

    /**
     * @param pharmacyName the pharmacyName to set
     */
    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    /**
     * @return String return the datee
     */
    public String getDatee() {
        return datee;
    }

    /**
     * @param datee the datee to set
     */
    public void setDatee(String datee) {
        this.datee = datee;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return double return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
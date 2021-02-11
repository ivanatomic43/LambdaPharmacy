package com.example.pharmacybackend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.MedicineStatus;

@Entity
@Table(name = "PHARMACY_MEDICINES")
public class PharmacyMedicine implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    private Medicine medicine;

    @OneToOne
    private Pharmacy pharmacy;

    @Enumerated(EnumType.STRING)
    private MedicineStatus statusInPharmacy;

    @Column
    private int quantity;

    @Column
    private double price;

    @Column
    private Date priceLastsTo;

    @Version
    private int version;

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
     * @return Medicine return the medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * @param medicine the medicine to set
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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
     * @return MedicineStatus return the statusInPharmacy
     */
    public MedicineStatus getStatusInPharmacy() {
        return statusInPharmacy;
    }

    /**
     * @param statusInPharmacy the statusInPharmacy to set
     */
    public void setStatusInPharmacy(MedicineStatus statusInPharmacy) {
        this.statusInPharmacy = statusInPharmacy;
    }

    /**
     * @return double return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
     * @return Date return the priceLastsTo
     */
    public Date getPriceLastsTo() {
        return priceLastsTo;
    }

    /**
     * @param priceLastsTo the priceLastsTo to set
     */
    public void setPriceLastsTo(Date priceLastsTo) {
        this.priceLastsTo = priceLastsTo;
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

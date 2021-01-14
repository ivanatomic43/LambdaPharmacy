package com.example.pharmacybackend.model;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.MedicineStatus;

@Entity
@Table(name = "PHARMACY_MEDICINES")
public class PharmacyMedicine {

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
    private double quantity;

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

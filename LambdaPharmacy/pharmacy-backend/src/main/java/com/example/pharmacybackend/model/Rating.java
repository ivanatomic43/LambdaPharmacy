package com.example.pharmacybackend.model;

import java.io.Serializable;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.RateStatus;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 918061230652109647L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private int rate;

    @Enumerated(EnumType.STRING)
    private RateStatus rateStatus;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Dermatologist dermatologist;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Pharmacist pharmacist;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Pharmacy pharmacy;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private PharmacyMedicine medicine;

    @Version
    private int version;

    public Rating() {
        super();
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
     * @return int return the ocena
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param ocena the ocena to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return EmployedDermatologist return the dermatologist
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
     * @return PharmacyMedicine return the medicine
     */
    public PharmacyMedicine getMedicine() {
        return medicine;
    }

    /**
     * @param medicine the medicine to set
     */
    public void setMedicine(PharmacyMedicine medicine) {
        this.medicine = medicine;
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
     * @return RateStatus return the rateStatus
     */
    public RateStatus getRateStatus() {
        return rateStatus;
    }

    /**
     * @param rateStatus the rateStatus to set
     */
    public void setRateStatus(RateStatus rateStatus) {
        this.rateStatus = rateStatus;
    }

}

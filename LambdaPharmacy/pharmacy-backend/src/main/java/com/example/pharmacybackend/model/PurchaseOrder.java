package com.example.pharmacybackend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.OrderStatus;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private Date date;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "pharmacy_administrator_id")
    private PharmacyAdministrator admin;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

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
     * @return OrderStatus return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return PharmacyAdministrator return the admin
     */
    public PharmacyAdministrator getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(PharmacyAdministrator admin) {
        this.admin = admin;
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
     * @return List<OrderItem> return the items
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * @return List<Offer> return the offers
     */
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
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

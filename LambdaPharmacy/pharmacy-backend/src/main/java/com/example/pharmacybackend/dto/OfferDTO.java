package com.example.pharmacybackend.dto;

import java.util.Date;

public class OfferDTO {

    private Long id;
    private Long orderID;

    private String deliveryTimee;
    private Date deliveryTime;
    private double totalPrice;

    private String pharmacyName;
    private String supplierName;
    private String supplierSurname;

    private String status;

    public OfferDTO() {
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
     * @return Long return the orderID
     */
    public Long getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    /**
     * @return double return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
     * @return String return the deliveryTimee
     */
    public String getDeliveryTimee() {
        return deliveryTimee;
    }

    /**
     * @param deliveryTimee the deliveryTimee to set
     */
    public void setDeliveryTimee(String deliveryTimee) {
        this.deliveryTimee = deliveryTimee;
    }

    /**
     * @return Date return the deliveryTime
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * @param deliveryTime the deliveryTime to set
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * @return String return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return String return the supplierSurname
     */
    public String getSupplierSurname() {
        return supplierSurname;
    }

    /**
     * @param supplierSurname the supplierSurname to set
     */
    public void setSupplierSurname(String supplierSurname) {
        this.supplierSurname = supplierSurname;
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

}

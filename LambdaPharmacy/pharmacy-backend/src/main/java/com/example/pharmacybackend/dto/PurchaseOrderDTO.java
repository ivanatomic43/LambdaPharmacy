package com.example.pharmacybackend.dto;

import java.util.Date;
import java.util.List;

import com.example.pharmacybackend.enumerations.OrderStatus;
import com.example.pharmacybackend.model.OrderItem;

public class PurchaseOrderDTO {

    private Long id;
    private Long adminID;
    private Long pharmacyID;

    private Date date;
    private String orderDatee;
    private String status;

    private OrderStatus statuss;

    private List<String> medicineName;
    private List<OrderItemDTO> items;

    private String name;
    private String surname;
    private String pharmacyName;

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(List<OrderItemDTO> items, Date date) {
        this.items = items;
        this.date = date;
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
     * @return Long return the adminID
     */
    public Long getAdminID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setAdminID(Long adminID) {
        this.adminID = adminID;
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
     * @return String return the orderDatee
     */
    public String getOrderDatee() {
        return orderDatee;
    }

    /**
     * @param orderDatee the orderDatee to set
     */
    public void setOrderDatee(String orderDatee) {
        this.orderDatee = orderDatee;
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
     * @return List<String> return the medicineName
     */
    public List<String> getMedicineName() {
        return medicineName;
    }

    /**
     * @param medicineName the medicineName to set
     */
    public void setMedicineName(List<String> medicineName) {
        this.medicineName = medicineName;
    }

    /**
     * @return OrderStatus return the statuss
     */
    public OrderStatus getStatuss() {
        return statuss;
    }

    /**
     * @param statuss the statuss to set
     */
    public void setStatuss(OrderStatus statuss) {
        this.statuss = statuss;
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
    public List<OrderItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

}

package com.example.pharmacybackend.dto;

public class OrderItemDTO {

    private Long oid;
    private Long id;
    private Long purchaseOrderID;

    private String medicineName;
    private int quantity;

    public OrderItemDTO() {

    }

    public OrderItemDTO(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    /**
     * @return Long return the oid
     */
    public Long getOid() {
        return oid;
    }

    /**
     * @param oid the oid to set
     */
    public void setOid(Long oid) {
        this.oid = oid;
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
     * @return Long return the purchaseOrderID
     */
    public Long getPurchaseOrderID() {
        return purchaseOrderID;
    }

    /**
     * @param purchaseOrderID the purchaseOrderID to set
     */
    public void setPurchaseOrderID(Long purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
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
     * @return int return the quantity
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

}

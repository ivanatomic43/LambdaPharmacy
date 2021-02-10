package com.example.pharmacybackend.dto;

public class CategoryDTO {

    private Long id;
    private String type;
    private int discount;
    private int pointsBorder;

    private String appointmentType;
    private int appPoints;

    public CategoryDTO() {
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
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return int return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return int return the pointsBorder
     */
    public int getPointsBorder() {
        return pointsBorder;
    }

    /**
     * @param pointsBorder the pointsBorder to set
     */
    public void setPointsBorder(int pointsBorder) {
        this.pointsBorder = pointsBorder;
    }

    /**
     * @return String return the appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * @param appointmentType the appointmentType to set
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * @return int return the appPoints
     */
    public int getAppPoints() {
        return appPoints;
    }

    /**
     * @param appPoints the appPoints to set
     */
    public void setAppPoints(int appPoints) {
        this.appPoints = appPoints;
    }

}

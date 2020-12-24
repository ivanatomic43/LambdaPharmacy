package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;

public class DermatologistDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateFrom;
    private Date dateTo;
    private LocalTime workFrom;
    private LocalTime workTo;

    public DermatologistDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public DermatologistDTO(Long id, Date dateFrom, Date dateTo, LocalTime workFrom, LocalTime workTo) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.workFrom = workFrom;
        this.workTo = workTo;
    }

    public DermatologistDTO(Long id, String firstName, String lastName, LocalTime workFrom, LocalTime workTo) {
        this.id = id;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.workFrom = workFrom;
        this.workTo = workTo;
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
     * @return Date return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return Date return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return LocalTime return the workFrom
     */
    public LocalTime getWorkFrom() {
        return workFrom;
    }

    /**
     * @param workFrom the workFrom to set
     */
    public void setWorkFrom(LocalTime workFrom) {
        this.workFrom = workFrom;
    }

    /**
     * @return LocalTime return the workTo
     */
    public LocalTime getWorkTo() {
        return workTo;
    }

    /**
     * @param workTo the workTo to set
     */
    public void setWorkTo(LocalTime workTo) {
        this.workTo = workTo;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
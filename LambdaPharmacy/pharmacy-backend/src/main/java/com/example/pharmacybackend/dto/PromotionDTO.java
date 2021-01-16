package com.example.pharmacybackend.dto;

import java.util.Date;

public class PromotionDTO {

    private Long id;
    private String description;
    private Date dateFrom;
    private Date dateTo;

    private String dateFromm;
    private String dateToo;

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
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return String return the dateFromm
     */
    public String getDateFromm() {
        return dateFromm;
    }

    /**
     * @param dateFromm the dateFromm to set
     */
    public void setDateFromm(String dateFromm) {
        this.dateFromm = dateFromm;
    }

    /**
     * @return String return the dateToo
     */
    public String getDateToo() {
        return dateToo;
    }

    /**
     * @param dateToo the dateToo to set
     */
    public void setDateToo(String dateToo) {
        this.dateToo = dateToo;
    }

}

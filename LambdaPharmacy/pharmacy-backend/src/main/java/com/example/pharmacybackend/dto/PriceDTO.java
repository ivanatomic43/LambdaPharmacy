package com.example.pharmacybackend.dto;

import java.util.Date;

public class PriceDTO {

    private Long id;
    private String name;
    private double price;
    private Date lastsFrom;

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
     * @return Date return the lastsFrom
     */
    public Date getLastsFrom() {
        return lastsFrom;
    }

    /**
     * @param lastsFrom the lastsFrom to set
     */
    public void setLastsFrom(Date lastsFrom) {
        this.lastsFrom = lastsFrom;
    }

}

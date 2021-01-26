package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class DermatologistDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateFrom;
    private Date dateTo;
    private LocalTime workFrom;
    private LocalTime workTo;
    private String from;
    private String to;

    private String dateFromm;
    private String dateToo;

    private List<String> allPharm;
    private double price;
    private double rating;
    private String pharmacyName;
    private String role;
    private String rateStatus;
    private double myRate;

    public DermatologistDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public DermatologistDTO(Long id, Date dateFrom, Date dateTo, LocalTime workFrom, LocalTime workTo, double price) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.workFrom = workFrom;
        this.workTo = workTo;
        this.price = price;
    }

    public DermatologistDTO(Long id, String firstName, String lastName, LocalTime workFrom, LocalTime workTo) {
        this.id = id;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.workFrom = workFrom;
        this.workTo = workTo;
    }

    public DermatologistDTO(Long id, String firstName, String lastName, String dateFromm, String dateToo, String from,
            String to, String pharmacyName, double price, double rating, String role, String rateStatus,
            double myRate) {
        this.id = id;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.from = from;
        this.to = to;
        this.dateFromm = dateFromm;
        this.dateToo = dateToo;
        this.pharmacyName = pharmacyName;
        this.rating = rating;
        this.price = price;
        this.role = role;
        this.rateStatus = rateStatus;
        this.myRate = myRate;
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

    /**
     * @return String return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return String return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return List<PharmacyDTO> return the allPharm
     */
    public List<String> getAllPharm() {
        return allPharm;
    }

    /**
     * @param allPharm the allPharm to set
     */
    public void setAllPharm(List<String> allPharm) {
        this.allPharm = allPharm;
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
     * @return double return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
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
     * @return String return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return String return the rateStatus
     */
    public String getRateStatus() {
        return rateStatus;
    }

    /**
     * @param rateStatus the rateStatus to set
     */
    public void setRateStatus(String rateStatus) {
        this.rateStatus = rateStatus;
    }

    /**
     * @return double return the myRate
     */
    public double getMyRate() {
        return myRate;
    }

    /**
     * @param myRate the myRate to set
     */
    public void setMyRate(double myRate) {
        this.myRate = myRate;
    }

}
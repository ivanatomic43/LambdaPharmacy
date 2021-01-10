package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;

public class PharmacistDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private LocalTime workFrom;
    private LocalTime workTo;
    private String from;
    private String to;
    private double price;

    public PharmacistDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public PharmacistDTO(Long id, String firstName, String lastName, String username, String email, String password,
            String address, String phoneNumber, LocalTime workFrom, LocalTime workTo, double price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;

        this.workFrom = workFrom;
        this.workTo = workTo;
        this.price = price;
    }

    public PharmacistDTO(Long id, String firstName, String lastName, LocalTime workFrom, LocalTime workTo,
            double price) {
        this.id = id;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.workFrom = workFrom;
        this.workTo = workTo;
        this.price = price;
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
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

}
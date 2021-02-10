package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;

public class AppointmentDTO {

    private Long id;
    private Long pharmacyID;
    private Long dermatologistID;
    private Long pharmacistID;

    private Date dateOfAppointment;
    private String dateOfAppointmentt;
    private LocalTime meetingTime;
    private String meetingTimee;
    private long duration;
    private double price;

    private String pharmacyName;
    private String firstName;
    private String lastName;
    private double rating;

    private String role;
    private String type;
    private int loyaltyPoints;

    public AppointmentDTO() {
    }

    // front
    public AppointmentDTO(Long pharmacyID, Long dermatologistID, Date dateOfAppointment, LocalTime meetingTime,
            long duration, double price) {
        this.pharmacyID = pharmacyID;
        this.dermatologistID = dermatologistID;
        this.dateOfAppointment = dateOfAppointment;
        this.meetingTime = meetingTime;
        this.duration = duration;
        this.price = price;
    }

    public AppointmentDTO(Long id, String dateOfAppointmentt, LocalTime meetingTime, long duration, double price,
            String firstName, String lastName, double rating, int loyaltyPoints) {
        this.id = id;
        this.dateOfAppointmentt = dateOfAppointmentt;
        this.meetingTime = meetingTime;
        this.duration = duration;
        this.price = price;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.loyaltyPoints = loyaltyPoints;
    }

    public AppointmentDTO(Long id, String dateOfAppointmentt, LocalTime meetingTime, long duration, double price,
            String firstName, String lastName, double rating, String role, String type, int loyaltyPoints) {
        this.id = id;
        this.dateOfAppointmentt = dateOfAppointmentt;
        this.meetingTime = meetingTime;
        this.duration = duration;
        this.price = price;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.role = role;
        this.type = type;
        this.loyaltyPoints = loyaltyPoints;
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
     * @return Long return the dermatologistID
     */
    public Long getDermatologistID() {
        return dermatologistID;
    }

    /**
     * @param dermatologistID the dermatologistID to set
     */
    public void setDermatologistID(Long dermatologistID) {
        this.dermatologistID = dermatologistID;
    }

    /**
     * @return Long return the pharmacistID
     */
    public Long getPharmacistID() {
        return pharmacistID;
    }

    /**
     * @param pharmacistID the pharmacistID to set
     */
    public void setPharmacistID(Long pharmacistID) {
        this.pharmacistID = pharmacistID;
    }

    /**
     * @return Date return the dateOfAppointment
     */
    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    /**
     * @param dateOfAppointment the dateOfAppointment to set
     */
    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    /**
     * @return LocalTime return the meetingTime
     */
    public LocalTime getMeetingTime() {
        return meetingTime;
    }

    /**
     * @param meetingTime the meetingTime to set
     */
    public void setMeetingTime(LocalTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    /**
     * @return double return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
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
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * @return String return the dateOfAppointmentt
     */
    public String getDateOfAppointmentt() {
        return dateOfAppointmentt;
    }

    /**
     * @param dateOfAppointmentt the dateOfAppointmentt to set
     */
    public void setDateOfAppointmentt(String dateOfAppointmentt) {
        this.dateOfAppointmentt = dateOfAppointmentt;
    }

    /**
     * @return String return the meetingTimee
     */
    public String getMeetingTimee() {
        return meetingTimee;
    }

    /**
     * @param meetingTimee the meetingTimee to set
     */
    public void setMeetingTimee(String meetingTimee) {
        this.meetingTimee = meetingTimee;
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
     * @return int return the loyaltyPoints
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * @param loyaltyPoints the loyaltyPoints to set
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

}

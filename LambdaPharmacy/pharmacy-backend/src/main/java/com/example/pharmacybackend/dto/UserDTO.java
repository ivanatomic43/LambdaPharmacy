package com.example.pharmacybackend.dto;

import com.example.pharmacybackend.model.User;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String username;
    private String phoneNumber;
    private String email;
    private String role;
    private int penalty;
    private int loyaltyPoints;

    public UserDTO() {

    }

    public UserDTO(User u) {
        this(u.getId(), u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getEmail(),
                u.getAddress(), u.getPhoneNumber(), u.getAuthority().toString(), u.getPenalty(), u.getLoyaltyPoints());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(Long id, String firstName, String lastName, String username, String password, String email,
            String address, String phoneNumber, String role, int penalty, int loyaltyPoints) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.penalty = penalty;
        this.loyaltyPoints = loyaltyPoints;

    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public UserDTO(Long id, String firstName, String lastName, String username, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;

    }

    /*
     * public UserDTO(User u){
     * this(u.getId(),u.getFirstName(),u.getLastName(),u.getAddress(),u.getPassword(
     * ),u.getUsername()); }
     */
    @Override
    public String toString() {
        return "PersonDTO{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", address='" + address + '\'' + ", password='" + password + '\'' + ", username='" + username + '\''
                + '}';
    }

    /**
     * @return int return the penalty
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     * @param penalty the penalty to set
     */
    public void setPenalty(int penalty) {
        this.penalty = penalty;
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
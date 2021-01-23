package com.example.pharmacybackend.dto;

public class ComplaintDTO {

    private Long id;
    private Long myID;
    private Long userID;
    private Long dermID;
    private Long pharmID;
    private Long pharmacyID;

    private String text;
    private String userName;
    private String userSurname;

    private String firstName;
    private String lastName;
    private String pharmacyName;

    private String status;
    private String refersName;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Long myID, String text) {
        this.myID = myID;
        this.text = text;
    }

    public ComplaintDTO(Long id, Long userID, Long dermID, Long pharmID, Long pharmacyID, String text, String userName,
            String userSurname, String firstName, String lastName, String pharmacyName, String status,
            String refersName) {
        this.id = id;
        this.userID = userID;
        this.dermID = dermID;
        this.pharmID = pharmID;
        this.pharmacyID = pharmacyID;
        this.text = text;
        this.userName = userName;
        this.userSurname = userSurname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.refersName = refersName;
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
     * @return Long return the myID
     */
    public Long getMyID() {
        return myID;
    }

    /**
     * @param myID the myID to set
     */
    public void setMyID(Long myID) {
        this.myID = myID;
    }

    /**
     * @return Long return the userID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * @return Long return the dermID
     */
    public Long getDermID() {
        return dermID;
    }

    /**
     * @param dermID the dermID to set
     */
    public void setDermID(Long dermID) {
        this.dermID = dermID;
    }

    /**
     * @return Long return the pharmID
     */
    public Long getPharmID() {
        return pharmID;
    }

    /**
     * @param pharmID the pharmID to set
     */
    public void setPharmID(Long pharmID) {
        this.pharmID = pharmID;
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
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return String return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return String return the userSurname
     */
    public String getUserSurname() {
        return userSurname;
    }

    /**
     * @param userSurname the userSurname to set
     */
    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
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
     * @return String return the refersName
     */
    public String getRefersName() {
        return refersName;
    }

    /**
     * @param refersName the refersName to set
     */
    public void setRefersName(String refersName) {
        this.refersName = refersName;
    }

}

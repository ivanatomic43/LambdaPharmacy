package com.example.pharmacybackend.dto;

public class VacationDTO {

    private Long id;
    private Long userID;
    private String vacationFrom;
    private String vacationTo;

    private String name;
    private String surname;
    private String role;
    private String status;

    private String denyText;

    public VacationDTO() {
    }

    public VacationDTO(Long id) {
        this.id = id;
    }

    public VacationDTO(Long id, Long userID, String vacationFrom, String vacationTo, String name, String surname,
            String status, String role) {
        this.id = id;
        this.userID = userID;
        this.vacationFrom = vacationFrom;
        this.vacationTo = vacationTo;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.role = role;
    }

    public VacationDTO(Long id, Long userID, String denyText) {
        this.id = id;
        this.userID = userID;
        this.denyText = denyText;
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
     * @return String return the vacationFrom
     */
    public String getVacationFrom() {
        return vacationFrom;
    }

    /**
     * @param vacationFrom the vacationFrom to set
     */
    public void setVacationFrom(String vacationFrom) {
        this.vacationFrom = vacationFrom;
    }

    /**
     * @return String return the vacationTo
     */
    public String getVacationTo() {
        return vacationTo;
    }

    /**
     * @param vacationTo the vacationTo to set
     */
    public void setVacationTo(String vacationTo) {
        this.vacationTo = vacationTo;
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
     * @return String return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
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
     * @return String return the denyText
     */
    public String getDenyText() {
        return denyText;
    }

    /**
     * @param denyText the denyText to set
     */
    public void setDenyText(String denyText) {
        this.denyText = denyText;
    }

}

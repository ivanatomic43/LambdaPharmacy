package com.example.pharmacybackend.dto;

//dto za preuzimanje vrednosti polja iz html forme za registraciju 
public class UserRequestDTO {

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String address;

	private String phoneNumber;

	private String email;

	private Long pharmacyID;

	public UserRequestDTO() {
	}

	public UserRequestDTO(String username, String password, String firstName, String lastName, String address,
			String phoneNumber, String email) {

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;

	}

	public UserRequestDTO(String username, String password, String firstName, String lastName, String address,
			String phoneNumber, String email, Long pharmacyID) {

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.pharmacyID = pharmacyID;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}

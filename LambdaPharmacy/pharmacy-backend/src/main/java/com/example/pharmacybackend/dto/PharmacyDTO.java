package com.example.pharmacybackend.dto;

import com.example.pharmacybackend.model.Pharmacy;

public class PharmacyDTO {

	private Long id;
	private String address;
	private String description;
	private String name;
	private double rating;
	private double price;

	private Long pharmacyAdministrator;
	private String firstName;
	private String lastName;

	private double myRate;

	public PharmacyDTO() {
	}

	public PharmacyDTO(Pharmacy p) {
		this(p.getId(), p.getAddress(), p.getDescription(), p.getName(), p.getRating());
	}

	public PharmacyDTO(Long id, String address, String description, String name, double rating, double price) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;
		this.price = price;
	}

	public PharmacyDTO(Long id, String address, String description, String name, double rating,
			Long pharmacyAdministrator, String firstName, String lastName) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;
		this.pharmacyAdministrator = pharmacyAdministrator;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public PharmacyDTO(Long id, String address, String description, String name, double rating) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;

	}

	public PharmacyDTO(Long id, String address, String description, double myRate, String name, double rating) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;
		this.myRate = myRate;

	}

	public PharmacyDTO(Long id, String address, String description, String name, double rating,
			Long pharmacyAdministrator) {
		this.id = id;
		this.address = address;
		this.description = description;
		this.name = name;
		this.rating = rating;
		this.pharmacyAdministrator = pharmacyAdministrator;

	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String return the price
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
	 * @return Long return the pharmacyAdministratorID
	 */
	public Long getPharmacyAdministrator() {
		return pharmacyAdministrator;
	}

	/**
	 * @param pharmacyAdministrator the pharmacyAdministratorID to set
	 */
	public void setPharmacyAdministrator(Long pharmacyAdministrator) {
		this.pharmacyAdministrator = pharmacyAdministrator;
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

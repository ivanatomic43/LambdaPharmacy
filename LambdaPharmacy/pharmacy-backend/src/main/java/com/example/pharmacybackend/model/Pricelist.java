package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name= "PRICELIST")
public class Pricelist {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column( nullable = false)
	private String item_name;
	
	@Column(nullable = false)
	private String price;
	
	@Column( nullable = false)
	private Date validFrom;
	
	@Column(nullable = false)
	private Date validTo;
	
	
	@OneToMany(mappedBy = "pricelist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pharmacy> listOfPharmacies = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Date getValidFrom() {
		return validFrom;
	}


	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}


	public Date getValidTo() {
		return validTo;
	}


	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}


	public List<Pharmacy> getListOfPharmacies() {
		return listOfPharmacies;
	}


	public void setListOfPharmacies(List<Pharmacy> listOfPharmacies) {
		this.listOfPharmacies = listOfPharmacies;
	}
	
}

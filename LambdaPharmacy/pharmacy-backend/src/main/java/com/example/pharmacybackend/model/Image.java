package com.example.pharmacybackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class Image {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
	
	@Lob
	private byte[] url;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	public Image() {
		super();
	}
	
	public Image(byte[] url, Pharmacy car) {
		super();
		this.url = url;
		this.pharmacy = pharmacy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getUrl() {
		return url;
	}

	public void setUrl(byte[] url) {
		this.url = url;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
}

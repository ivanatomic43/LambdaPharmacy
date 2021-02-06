package com.example.pharmacybackend.model;

import javax.persistence.*;

@Entity
@Table(name = "PRICELIST")
public class Pricelist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

}

package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "RECIPE")
public class Recipe {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Patient patient;
	
	@Column
	private Date date;
	

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "recipe_medicine",
    				joinColumns = @JoinColumn(name="recipe_id", referencedColumnName = "id"),
    				inverseJoinColumns = @JoinColumn(name ="medicine_id", referencedColumnName= "id"))
    private List<Medicine> listOfMedicines = new ArrayList<>();
	
	
}

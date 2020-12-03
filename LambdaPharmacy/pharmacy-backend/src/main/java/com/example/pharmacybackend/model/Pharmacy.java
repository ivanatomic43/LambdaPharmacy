package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="PHARMACY")
public class Pharmacy {

	private static final long serialVersionUID = 1L;
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    private String name;
	    
	    @Column(nullable = false)
	    private String address;
	   
	    @Column(nullable = false)
	    private String description;
	
	   // @Column(nullable = false)
	   // private List<Date> freeDates;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "pharmacy_dermatologist",
	            joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "dermatologist_id", referencedColumnName = "id"))
	    private List<Dermatologist> dermatologists ;
	
	
	    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Pharmacist> pharmacists = new ArrayList<>();
	    
	    @OneToMany(mappedBy="pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<PharmacyAdministrator> pharmacyAdministrators= new ArrayList<>();
	
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name= "pharmacy_medicine",
	    				joinColumns = @JoinColumn(name="pharmacy_id", referencedColumnName = "id"),
	    				inverseJoinColumns = @JoinColumn(name ="medicine_id", referencedColumnName= "id"))
	    private List<Medicine> listOfMedicines;
	
	    
	    @ManyToOne
	    private Pricelist pricelist;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
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


		/*public List<Date> getFreeDates() {
			return freeDates;
		}


		public void setFreeDates(List<Date> freeDates) {
			this.freeDates = freeDates;
		}


		public List<Dermatologist> getDermatologists() {
			return dermatologists;
		}


		public void setDermatologists(List<Dermatologist> dermatologists) {
			this.dermatologists = dermatologists;
		}

*/
		public List<Pharmacist> getPharmacists() {
			return pharmacists;
		}


		public void setPharmacists(List<Pharmacist> pharmacists) {
			this.pharmacists = pharmacists;
		}

/*
		public List<Medicine> getListOfMedicines() {
			return listOfMedicines;
		}


		public void setListOfMedicines(List<Medicine> listOfMedicines) {
			this.listOfMedicines = listOfMedicines;
		}
*/

		public Pricelist getPricelist() {
			return pricelist;
		}


		public void setPricelist(Pricelist pricelist) {
			this.pricelist = pricelist;
		}
	
	    
	
}

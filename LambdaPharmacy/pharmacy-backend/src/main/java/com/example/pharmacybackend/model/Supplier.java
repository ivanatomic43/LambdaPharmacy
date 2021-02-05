package com.example.pharmacybackend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("USER_SUPPLIER")
public class Supplier extends User {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

}

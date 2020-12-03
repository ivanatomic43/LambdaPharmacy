package com.example.pharmacybackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("USER_SUPPLIER")
public class Supplier extends User{

}

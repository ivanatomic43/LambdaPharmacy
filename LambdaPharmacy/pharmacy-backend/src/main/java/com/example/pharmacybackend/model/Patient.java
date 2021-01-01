package com.example.pharmacybackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("USER_PATIENT")
public class Patient extends User {

	private static final long serialVersionUID = 1L;

}

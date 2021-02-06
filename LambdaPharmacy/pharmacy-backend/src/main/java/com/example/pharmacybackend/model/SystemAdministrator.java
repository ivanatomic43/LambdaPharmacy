package com.example.pharmacybackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("USER_SYS_ADMIN")
public class SystemAdministrator extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}

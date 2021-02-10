package com.example.pharmacybackend.model;

import javax.persistence.*;

import com.example.pharmacybackend.enumerations.AppointmentType;

@Entity
public class AppointmentLoyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    @Column
    private int points;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return AppointmentType return the type
     */
    public AppointmentType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AppointmentType type) {
        this.type = type;
    }

    /**
     * @return int return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

}

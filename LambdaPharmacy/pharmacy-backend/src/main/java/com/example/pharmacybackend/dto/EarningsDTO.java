package com.example.pharmacybackend.dto;

public class EarningsDTO {

    public String month;
    public double appointmentCounter;

    public EarningsDTO() {
    }

    public EarningsDTO(String month, double total) {
        super();
        this.month = month;
        this.appointmentCounter = total;
    }
}

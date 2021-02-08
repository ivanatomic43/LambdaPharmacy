package com.example.pharmacybackend.dto;

public class StatisticDTO {

    public String month;
    public int appointmentCounter;

    public StatisticDTO() {

    }

    public StatisticDTO(String month, int appointmentCounter) {
        super();
        this.month = month;
        this.appointmentCounter = appointmentCounter;
    }

}

package com.example.pharmacybackend.dto;

import java.time.LocalTime;
import java.util.Date;

public class SearchPharmacistParams {

    private Date date;
    private LocalTime time;

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return LocalTime return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

}

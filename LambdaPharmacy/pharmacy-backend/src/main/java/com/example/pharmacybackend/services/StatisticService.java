package com.example.pharmacybackend.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;

import com.example.pharmacybackend.dto.EarningsDTO;
import com.example.pharmacybackend.dto.StatisticDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.MedicineStatus;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.MedicineReservation;
import com.example.pharmacybackend.repository.AppointmentRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.ReservationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StatisticService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    public List<StatisticDTO> getAppointmentStatistic(Long pharmacyID) throws ParseException {

        List<StatisticDTO> retList = new ArrayList<>();

        List<Appointment> allApp = appointmentRepository.findAll();

        int year = Year.now().getValue();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();

        // months
        cal.set(year, 0, 1);
        String jan1_str = format.format(cal.getTime());
        Date jan1 = format.parse(jan1_str);

        cal.set(year, 0, 31);
        String jan31_str = format.format(cal.getTime());
        Date jan31 = format.parse(jan31_str);

        cal.set(year, 1, 1);
        String feb1_str = format.format(cal.getTime());
        Date feb1 = format.parse(feb1_str);

        cal.set(year, 1, 28);
        String feb28_str = format.format(cal.getTime());
        Date feb28 = format.parse(feb28_str);

        cal.set(year, 2, 1);
        String mart1_str = format.format(cal.getTime());
        Date mart1 = format.parse(mart1_str);

        cal.set(year, 2, 31);
        String mart31_str = format.format(cal.getTime());
        Date mart31 = format.parse(mart31_str);

        cal.set(year, 3, 1);
        String apr1_str = format.format(cal.getTime());
        Date apr1 = format.parse(apr1_str);

        cal.set(year, 3, 30);
        String apr30_str = format.format(cal.getTime());
        Date apr30 = format.parse(apr30_str);

        cal.set(year, 4, 1);
        String may1_str = format.format(cal.getTime());
        Date may1 = format.parse(may1_str);

        cal.set(year, 4, 31);
        String may31_str = format.format(cal.getTime());
        Date may31 = format.parse(may31_str);

        cal.set(year, 5, 1);
        String jun1_str = format.format(cal.getTime());
        Date jun1 = format.parse(jun1_str);

        cal.set(year, 5, 30);
        String jun30_str = format.format(cal.getTime());
        Date jun30 = format.parse(jun30_str);

        cal.set(year, 6, 1);
        String jul1_str = format.format(cal.getTime());
        Date jul1 = format.parse(jul1_str);

        cal.set(year, 6, 31);
        String jul31_str = format.format(cal.getTime());
        Date jul31 = format.parse(jul31_str);

        cal.set(year, 7, 1);
        String aug1_str = format.format(cal.getTime());
        Date aug1 = format.parse(aug1_str);

        cal.set(year, 7, 31);
        String aug31_str = format.format(cal.getTime());
        Date aug31 = format.parse(aug31_str);

        cal.set(year, 8, 1);
        String sep1_str = format.format(cal.getTime());
        Date sep1 = format.parse(sep1_str);

        cal.set(year, 8, 30);
        String sep30_str = format.format(cal.getTime());
        Date sep30 = format.parse(sep30_str);

        cal.set(year, 9, 1);
        String oct1_str = format.format(cal.getTime());
        Date oct1 = format.parse(oct1_str);

        cal.set(year, 9, 31);
        String oct31_str = format.format(cal.getTime());
        Date oct31 = format.parse(oct31_str);

        cal.set(year, 10, 1);
        String nov1_str = format.format(cal.getTime());
        Date nov1 = format.parse(nov1_str);

        cal.set(year, 10, 30);
        String nov30_str = format.format(cal.getTime());
        Date nov30 = format.parse(nov30_str);

        cal.set(year, 11, 1);
        String dec1_str = format.format(cal.getTime());
        Date dec1 = format.parse(dec1_str);

        cal.set(year, 11, 31);
        String dec31_str = format.format(cal.getTime());
        Date dec31 = format.parse(dec31_str);

        int jan = 0, feb = 0, mart = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;

        for (Appointment a : allApp) {
            if (a.getStatus().equals(AppointmentStatus.DONE) && a.getPharmacy().getId() == pharmacyID) {

                Date myDate = a.getDateOfAppointment();
                if (jan1.equals(myDate) || jan31.equals(myDate) || (jan1.before(myDate) && jan31.after(myDate))) {
                    jan += 1;
                }

                if (feb1.equals(myDate) || feb28.equals(myDate) || (feb1.before(myDate) && feb28.after(myDate))) {
                    feb += 1;
                }

                if (mart1.equals(myDate) || mart31.equals(myDate) || (mart1.before(myDate) && mart31.after(myDate))) {
                    mart += 1;
                }

                if (apr1.equals(myDate) || apr30.equals(myDate) || (apr1.before(myDate) && apr30.after(myDate))) {
                    apr += 1;
                }

                if (may1.equals(myDate) || may31.equals(myDate) || (may1.before(myDate) && may31.after(myDate))) {
                    may += 1;
                }

                if (jun1.equals(myDate) || jun30.equals(myDate) || (jun1.before(myDate) && jun30.after(myDate))) {
                    jun += 1;
                }
                if (jul1.equals(myDate) || jul31.equals(myDate) || (jul1.before(myDate) && jul31.after(myDate))) {
                    jul += 1;
                }
                if (aug1.equals(myDate) || aug31.equals(myDate) || (aug1.before(myDate) && aug31.after(myDate))) {
                    aug += 1;
                }

                if (sep1.equals(myDate) || sep30.equals(myDate) || (sep1.before(myDate) && sep30.after(myDate))) {
                    sep += 1;
                }
                if (oct1.equals(myDate) || oct31.equals(myDate) || (oct1.before(myDate) && oct31.after(myDate))) {
                    oct += 1;
                }
                if (nov1.equals(myDate) || nov30.equals(myDate) || (nov1.before(myDate) && nov30.after(myDate))) {
                    nov += 1;
                }
                if (dec1.equals(myDate) || dec31.equals(myDate) || (dec1.before(myDate) && dec31.after(myDate))) {
                    dec += 1;
                }

            }

        }

        StatisticDTO january = new StatisticDTO("jan", jan);
        retList.add(january);
        StatisticDTO february = new StatisticDTO("feb", feb);
        retList.add(february);
        StatisticDTO march = new StatisticDTO("mar", mart);
        retList.add(march);
        StatisticDTO april = new StatisticDTO("apr", apr);
        retList.add(april);
        StatisticDTO mayy = new StatisticDTO("may", may);
        retList.add(mayy);
        StatisticDTO june = new StatisticDTO("jun", jun);
        retList.add(june);
        StatisticDTO july = new StatisticDTO("jul", jul);
        retList.add(july);
        StatisticDTO august = new StatisticDTO("aug", aug);
        retList.add(august);
        StatisticDTO september = new StatisticDTO("sep", sep);
        retList.add(september);
        StatisticDTO october = new StatisticDTO("oct", oct);
        retList.add(october);
        StatisticDTO november = new StatisticDTO("nov", nov);
        retList.add(november);
        StatisticDTO december = new StatisticDTO("dec", dec);
        retList.add(december);

        return retList;
    }

    public List<StatisticDTO> getMedicinesStatistic(Long pharmacyID) throws ParseException {

        List<StatisticDTO> retList = new ArrayList<>();

        List<MedicineReservation> medRes = reservationRepository.findAll();

        int year = Year.now().getValue();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();

        // months
        cal.set(year, 0, 1);
        String jan1_str = format.format(cal.getTime());
        Date jan1 = format.parse(jan1_str);

        cal.set(year, 0, 31);
        String jan31_str = format.format(cal.getTime());
        Date jan31 = format.parse(jan31_str);

        cal.set(year, 1, 1);
        String feb1_str = format.format(cal.getTime());
        Date feb1 = format.parse(feb1_str);

        cal.set(year, 1, 28);
        String feb28_str = format.format(cal.getTime());
        Date feb28 = format.parse(feb28_str);

        cal.set(year, 2, 1);
        String mart1_str = format.format(cal.getTime());
        Date mart1 = format.parse(mart1_str);

        cal.set(year, 2, 31);
        String mart31_str = format.format(cal.getTime());
        Date mart31 = format.parse(mart31_str);

        cal.set(year, 3, 1);
        String apr1_str = format.format(cal.getTime());
        Date apr1 = format.parse(apr1_str);

        cal.set(year, 3, 30);
        String apr30_str = format.format(cal.getTime());
        Date apr30 = format.parse(apr30_str);

        cal.set(year, 4, 1);
        String may1_str = format.format(cal.getTime());
        Date may1 = format.parse(may1_str);

        cal.set(year, 4, 31);
        String may31_str = format.format(cal.getTime());
        Date may31 = format.parse(may31_str);

        cal.set(year, 5, 1);
        String jun1_str = format.format(cal.getTime());
        Date jun1 = format.parse(jun1_str);

        cal.set(year, 5, 30);
        String jun30_str = format.format(cal.getTime());
        Date jun30 = format.parse(jun30_str);

        cal.set(year, 6, 1);
        String jul1_str = format.format(cal.getTime());
        Date jul1 = format.parse(jul1_str);

        cal.set(year, 6, 31);
        String jul31_str = format.format(cal.getTime());
        Date jul31 = format.parse(jul31_str);

        cal.set(year, 7, 1);
        String aug1_str = format.format(cal.getTime());
        Date aug1 = format.parse(aug1_str);

        cal.set(year, 7, 31);
        String aug31_str = format.format(cal.getTime());
        Date aug31 = format.parse(aug31_str);

        cal.set(year, 8, 1);
        String sep1_str = format.format(cal.getTime());
        Date sep1 = format.parse(sep1_str);

        cal.set(year, 8, 30);
        String sep30_str = format.format(cal.getTime());
        Date sep30 = format.parse(sep30_str);

        cal.set(year, 9, 1);
        String oct1_str = format.format(cal.getTime());
        Date oct1 = format.parse(oct1_str);

        cal.set(year, 9, 31);
        String oct31_str = format.format(cal.getTime());
        Date oct31 = format.parse(oct31_str);

        cal.set(year, 10, 1);
        String nov1_str = format.format(cal.getTime());
        Date nov1 = format.parse(nov1_str);

        cal.set(year, 10, 30);
        String nov30_str = format.format(cal.getTime());
        Date nov30 = format.parse(nov30_str);

        cal.set(year, 11, 1);
        String dec1_str = format.format(cal.getTime());
        Date dec1 = format.parse(dec1_str);

        cal.set(year, 11, 31);
        String dec31_str = format.format(cal.getTime());
        Date dec31 = format.parse(dec31_str);

        int jan = 0, feb = 0, mart = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;

        for (MedicineReservation m : medRes) {
            if (m.getStatus().equals(MedicineStatus.PICKED_UP) && m.getPharmacy().getId() == pharmacyID) {

                Date myDate = m.getDate();
                if (jan1.equals(myDate) || jan31.equals(myDate) || (jan1.before(myDate) && jan31.after(myDate))) {
                    jan += 1;
                }

                if (feb1.equals(myDate) || feb28.equals(myDate) || (feb1.before(myDate) && feb28.after(myDate))) {
                    feb += 1;
                }

                if (mart1.equals(myDate) || mart31.equals(myDate) || (mart1.before(myDate) && mart31.after(myDate))) {
                    mart += 1;
                }

                if (apr1.equals(myDate) || apr30.equals(myDate) || (apr1.before(myDate) && apr30.after(myDate))) {
                    apr += 1;
                }

                if (may1.equals(myDate) || may31.equals(myDate) || (may1.before(myDate) && may31.after(myDate))) {
                    may += 1;
                }

                if (jun1.equals(myDate) || jun30.equals(myDate) || (jun1.before(myDate) && jun30.after(myDate))) {
                    jun += 1;
                }
                if (jul1.equals(myDate) || jul31.equals(myDate) || (jul1.before(myDate) && jul31.after(myDate))) {
                    jul += 1;
                }
                if (aug1.equals(myDate) || aug31.equals(myDate) || (aug1.before(myDate) && aug31.after(myDate))) {
                    aug += 1;
                }

                if (sep1.equals(myDate) || sep30.equals(myDate) || (sep1.before(myDate) && sep30.after(myDate))) {
                    sep += 1;
                }
                if (oct1.equals(myDate) || oct31.equals(myDate) || (oct1.before(myDate) && oct31.after(myDate))) {
                    oct += 1;
                }
                if (nov1.equals(myDate) || nov30.equals(myDate) || (nov1.before(myDate) && nov30.after(myDate))) {
                    nov += 1;
                }
                if (dec1.equals(myDate) || dec31.equals(myDate) || (dec1.before(myDate) && dec31.after(myDate))) {
                    dec += 1;
                }

            }

        }

        StatisticDTO january = new StatisticDTO("jan", jan);
        retList.add(january);
        StatisticDTO february = new StatisticDTO("feb", feb);
        retList.add(february);
        StatisticDTO march = new StatisticDTO("mar", mart);
        retList.add(march);
        StatisticDTO april = new StatisticDTO("apr", apr);
        retList.add(april);
        StatisticDTO mayy = new StatisticDTO("may", may);
        retList.add(mayy);
        StatisticDTO june = new StatisticDTO("jun", jun);
        retList.add(june);
        StatisticDTO july = new StatisticDTO("jul", jul);
        retList.add(july);
        StatisticDTO august = new StatisticDTO("aug", aug);
        retList.add(august);
        StatisticDTO september = new StatisticDTO("sep", sep);
        retList.add(september);
        StatisticDTO october = new StatisticDTO("oct", oct);
        retList.add(october);
        StatisticDTO november = new StatisticDTO("nov", nov);
        retList.add(november);
        StatisticDTO december = new StatisticDTO("dec", dec);
        retList.add(december);

        return retList;
    }

    public List<EarningsDTO> getTotalEarnings(Long pharmacyID) throws ParseException {

        List<EarningsDTO> retList = new ArrayList<>();

        List<MedicineReservation> medRes = reservationRepository.findAll();
        List<Appointment> appList = appointmentRepository.findAll();

        int year = Year.now().getValue();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();

        // months
        cal.set(year, 0, 1);
        String jan1_str = format.format(cal.getTime());
        Date jan1 = format.parse(jan1_str);

        cal.set(year, 0, 31);
        String jan31_str = format.format(cal.getTime());
        Date jan31 = format.parse(jan31_str);

        cal.set(year, 1, 1);
        String feb1_str = format.format(cal.getTime());
        Date feb1 = format.parse(feb1_str);

        cal.set(year, 1, 28);
        String feb28_str = format.format(cal.getTime());
        Date feb28 = format.parse(feb28_str);

        cal.set(year, 2, 1);
        String mart1_str = format.format(cal.getTime());
        Date mart1 = format.parse(mart1_str);

        cal.set(year, 2, 31);
        String mart31_str = format.format(cal.getTime());
        Date mart31 = format.parse(mart31_str);

        cal.set(year, 3, 1);
        String apr1_str = format.format(cal.getTime());
        Date apr1 = format.parse(apr1_str);

        cal.set(year, 3, 30);
        String apr30_str = format.format(cal.getTime());
        Date apr30 = format.parse(apr30_str);

        cal.set(year, 4, 1);
        String may1_str = format.format(cal.getTime());
        Date may1 = format.parse(may1_str);

        cal.set(year, 4, 31);
        String may31_str = format.format(cal.getTime());
        Date may31 = format.parse(may31_str);

        cal.set(year, 5, 1);
        String jun1_str = format.format(cal.getTime());
        Date jun1 = format.parse(jun1_str);

        cal.set(year, 5, 30);
        String jun30_str = format.format(cal.getTime());
        Date jun30 = format.parse(jun30_str);

        cal.set(year, 6, 1);
        String jul1_str = format.format(cal.getTime());
        Date jul1 = format.parse(jul1_str);

        cal.set(year, 6, 31);
        String jul31_str = format.format(cal.getTime());
        Date jul31 = format.parse(jul31_str);

        cal.set(year, 7, 1);
        String aug1_str = format.format(cal.getTime());
        Date aug1 = format.parse(aug1_str);

        cal.set(year, 7, 31);
        String aug31_str = format.format(cal.getTime());
        Date aug31 = format.parse(aug31_str);

        cal.set(year, 8, 1);
        String sep1_str = format.format(cal.getTime());
        Date sep1 = format.parse(sep1_str);

        cal.set(year, 8, 30);
        String sep30_str = format.format(cal.getTime());
        Date sep30 = format.parse(sep30_str);

        cal.set(year, 9, 1);
        String oct1_str = format.format(cal.getTime());
        Date oct1 = format.parse(oct1_str);

        cal.set(year, 9, 31);
        String oct31_str = format.format(cal.getTime());
        Date oct31 = format.parse(oct31_str);

        cal.set(year, 10, 1);
        String nov1_str = format.format(cal.getTime());
        Date nov1 = format.parse(nov1_str);

        cal.set(year, 10, 30);
        String nov30_str = format.format(cal.getTime());
        Date nov30 = format.parse(nov30_str);

        cal.set(year, 11, 1);
        String dec1_str = format.format(cal.getTime());
        Date dec1 = format.parse(dec1_str);

        cal.set(year, 11, 31);
        String dec31_str = format.format(cal.getTime());
        Date dec31 = format.parse(dec31_str);

        double jan = 0, feb = 0, mart = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0,
                dec = 0;

        for (MedicineReservation m : medRes) {
            if (m.getStatus().equals(MedicineStatus.PICKED_UP) && m.getPharmacy().getId() == pharmacyID) {

                Date myDate = m.getDate();
                if (jan1.equals(myDate) || jan31.equals(myDate) || (jan1.before(myDate) && jan31.after(myDate))) {
                    jan += m.getPrice();
                }

                if (feb1.equals(myDate) || feb28.equals(myDate) || (feb1.before(myDate) && feb28.after(myDate))) {
                    feb += m.getPrice();
                }

                if (mart1.equals(myDate) || mart31.equals(myDate) || (mart1.before(myDate) && mart31.after(myDate))) {
                    mart += m.getPrice();
                }

                if (apr1.equals(myDate) || apr30.equals(myDate) || (apr1.before(myDate) && apr30.after(myDate))) {
                    apr += m.getPrice();
                }

                if (may1.equals(myDate) || may31.equals(myDate) || (may1.before(myDate) && may31.after(myDate))) {
                    may += m.getPrice();
                }

                if (jun1.equals(myDate) || jun30.equals(myDate) || (jun1.before(myDate) && jun30.after(myDate))) {
                    jun += m.getPrice();
                }
                if (jul1.equals(myDate) || jul31.equals(myDate) || (jul1.before(myDate) && jul31.after(myDate))) {
                    jul += m.getPrice();
                }
                if (aug1.equals(myDate) || aug31.equals(myDate) || (aug1.before(myDate) && aug31.after(myDate))) {
                    aug += m.getPrice();
                }

                if (sep1.equals(myDate) || sep30.equals(myDate) || (sep1.before(myDate) && sep30.after(myDate))) {
                    sep += m.getPrice();
                }
                if (oct1.equals(myDate) || oct31.equals(myDate) || (oct1.before(myDate) && oct31.after(myDate))) {
                    oct += m.getPrice();
                }
                if (nov1.equals(myDate) || nov30.equals(myDate) || (nov1.before(myDate) && nov30.after(myDate))) {
                    nov += m.getPrice();
                }
                if (dec1.equals(myDate) || dec31.equals(myDate) || (dec1.before(myDate) && dec31.after(myDate))) {
                    dec += m.getPrice();
                }

            }

        }

        for (Appointment a : appList) {
            if (a.getStatus().equals(AppointmentStatus.DONE) && a.getPharmacy().getId() == pharmacyID) {

                Date myDate = a.getDateOfAppointment();
                if (jan1.equals(myDate) || jan31.equals(myDate) || (jan1.before(myDate) && jan31.after(myDate))) {
                    jan += a.getPrice();
                }

                if (feb1.equals(myDate) || feb28.equals(myDate) || (feb1.before(myDate) && feb28.after(myDate))) {
                    feb += a.getPrice();
                }

                if (mart1.equals(myDate) || mart31.equals(myDate) || (mart1.before(myDate) && mart31.after(myDate))) {
                    mart += a.getPrice();
                }

                if (apr1.equals(myDate) || apr30.equals(myDate) || (apr1.before(myDate) && apr30.after(myDate))) {
                    apr += a.getPrice();
                }

                if (may1.equals(myDate) || may31.equals(myDate) || (may1.before(myDate) && may31.after(myDate))) {
                    may += a.getPrice();
                }

                if (jun1.equals(myDate) || jun30.equals(myDate) || (jun1.before(myDate) && jun30.after(myDate))) {
                    jun += a.getPrice();
                }
                if (jul1.equals(myDate) || jul31.equals(myDate) || (jul1.before(myDate) && jul31.after(myDate))) {
                    jul += a.getPrice();
                }
                if (aug1.equals(myDate) || aug31.equals(myDate) || (aug1.before(myDate) && aug31.after(myDate))) {
                    aug += a.getPrice();
                }

                if (sep1.equals(myDate) || sep30.equals(myDate) || (sep1.before(myDate) && sep30.after(myDate))) {
                    sep += a.getPrice();
                }
                if (oct1.equals(myDate) || oct31.equals(myDate) || (oct1.before(myDate) && oct31.after(myDate))) {
                    oct += a.getPrice();
                }
                if (nov1.equals(myDate) || nov30.equals(myDate) || (nov1.before(myDate) && nov30.after(myDate))) {
                    nov += a.getPrice();
                }
                if (dec1.equals(myDate) || dec31.equals(myDate) || (dec1.before(myDate) && dec31.after(myDate))) {
                    dec += a.getPrice();
                }

            }

        }

        EarningsDTO january = new EarningsDTO("jan", jan);
        retList.add(january);
        EarningsDTO february = new EarningsDTO("feb", feb);
        retList.add(february);
        EarningsDTO march = new EarningsDTO("mar", mart);
        retList.add(march);
        EarningsDTO april = new EarningsDTO("apr", apr);
        retList.add(april);
        EarningsDTO mayy = new EarningsDTO("may", may);
        retList.add(mayy);
        EarningsDTO june = new EarningsDTO("jun", jun);
        retList.add(june);
        EarningsDTO july = new EarningsDTO("jul", jul);
        retList.add(july);
        EarningsDTO august = new EarningsDTO("aug", aug);
        retList.add(august);
        EarningsDTO september = new EarningsDTO("sep", sep);
        retList.add(september);
        EarningsDTO october = new EarningsDTO("oct", oct);
        retList.add(october);
        EarningsDTO november = new EarningsDTO("nov", nov);
        retList.add(november);
        EarningsDTO december = new EarningsDTO("dec", dec);
        retList.add(december);

        return retList;
    }

}

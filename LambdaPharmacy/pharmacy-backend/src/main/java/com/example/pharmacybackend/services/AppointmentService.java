package com.example.pharmacybackend.services;

import com.example.pharmacybackend.repository.PatientRepository;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
//import java.util.Date;

import com.example.pharmacybackend.dto.AppointmentDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.*;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PatientService patientService;

    public Appointment findById(long id) {
        return this.appointmentRepository.findById(id);
    }

    public List<Appointment> getAll() {
        return this.appointmentRepository.findAll();
    }

    public Appointment update(Appointment ap) {
        return this.appointmentRepository.save(ap);
    }

    // dermatologist
    public AppointmentDTO createAppointment(AppointmentDTO newApp) {

        // checking if dermatologist is available
        Dermatologist dermatologist = dermatologistRepository.findOneById(newApp.getDermatologistID());
        Pharmacy pharmacy = pharmacyRepository.findOneById(newApp.getPharmacyID());

        LocalTime dermFrom = dermatologist.getWorkingFrom();
        LocalTime dermTo = dermatologist.getWorkingTo();

        LocalTime timeStart = newApp.getMeetingTime();
        LocalTime timeEnd = timeStart.plusHours(newApp.getDuration());

        System.out.println("TIME START:" + timeStart);
        System.out.println("TIME END: " + timeEnd);

        if (timeStart.compareTo(dermFrom) < 0 || timeEnd.compareTo(dermTo) > 0) {
            System.out.println("Time invalid");
            return null;
        }

        // checking other appointments
        List<Appointment> allApp = appointmentRepository.findAll();
        if (!allApp.isEmpty()) {

            for (Appointment app : allApp) {

                if (app.getDermatologist().getId() == dermatologist.getId()) {

                    if (app.getDateOfAppointment().compareTo(newApp.getDateOfAppointment()) == 0) {

                        LocalTime appStart = app.getMeetingTime();
                        LocalTime appEnd = appStart.plusHours(app.getDuration());

                        if (appStart.compareTo(newApp.getMeetingTime()) == 0 || timeStart.compareTo(appEnd) < 0) { // timeEnd
                            // -
                            // for
                            // new
                            // appointment
                            System.out.println("Time invalid, dermatologist is busy...");

                            return null;
                        }
                    }
                }

            }
        }

        Appointment a = new Appointment();
        a.setDateOfAppointment(newApp.getDateOfAppointment());
        a.setMeetingTime(newApp.getMeetingTime());
        a.setDuration(newApp.getDuration());
        a.setPrice(newApp.getPrice());
        a.setDermatologist(dermatologist);
        a.setPharmacy(pharmacy);
        a.setType(AppointmentType.EXAMINATION);
        a.setStatus(AppointmentStatus.FREE);
        appointmentRepository.save(a);
        // newApp.setId(a.getId());

        // adding a to dermatologist
        List<Appointment> reservedAppointments = dermatologist.getReservedAppointments();
        reservedAppointments.add(a);

        // adding a to pharmacy

        List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
        pharmacyAppointments.add(a);

        // save all
        dermatologistRepository.save(dermatologist);
        pharmacyRepository.save(pharmacy);

        // creating a dto
        String firstName = dermatologist.getFirstName();
        String lastName = dermatologist.getLastName();
        // double rating = dermatologist.getRating();

        System.out.println(firstName + lastName);

        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(a.getId());
        dto.setDateOfAppointment(a.getDateOfAppointment());
        dto.setDermatologistID(a.getDermatologist().getId());
        dto.setDuration(a.getDuration());
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setMeetingTime(a.getMeetingTime());
        // System.out.println(a.getPharmacy().getName()
        // dto.setPharmacyName(a.getPharmacy().getName());
        // dto.setRating(rating);
        dto.setPrice(a.getPrice());

        return dto;
    }

    public List<AppointmentDTO> getAllPredefined(Long id) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        List<Appointment> appList = pharmacy.getPharmacyAppointments();
        List<AppointmentDTO> retList = new ArrayList<>();

        for (Appointment a : appList) {
            if (a.getStatus().equals(AppointmentStatus.FREE) || a.getStatus().equals(AppointmentStatus.CANCELLED)) {
                AppointmentDTO dto = new AppointmentDTO();

                dto.setId(a.getId());
                dto.setDateOfAppointmentt(a.getDateOfAppointment().toString());
                dto.setDermatologistID(a.getDermatologist().getId());
                dto.setDuration(a.getDuration());
                dto.setFirstName(a.getDermatologist().getFirstName());
                dto.setLastName(a.getDermatologist().getLastName());
                dto.setMeetingTimee(a.getMeetingTime().toString());
                // dto.setPharmacyName(a.getPharmacy().getName());
                // dto.setRating(a.getDermatologist().getRating());
                dto.setPrice(a.getPrice());

                retList.add(dto);
            }
        }

        return retList;
    }

    public boolean reserveAppointment(Long id, Long userID) {

        boolean reserved = false;

        Appointment a = appointmentRepository.findOneById(id);
        Patient patient = patientRepository.findOneById(userID);

        a.setStatus(AppointmentStatus.RESERVED);
        a.setPatient(patient);

        // List<Appointment> myAppointments = patient.getAppointments();
        // myAppointments.add(a);

        appointmentRepository.save(a);
        patientRepository.save(patient);

        emailService.sendAppointmentReservationMail(patient);
        reserved = true;

        return reserved;
    }

    public List<AppointmentDTO> getAllPatientAppointments(Long id) {

        List<Appointment> appList = appointmentRepository.getByPatientId(id);
        List<AppointmentDTO> myList = new ArrayList<>();

        for (Appointment a : appList) {
            if (a.getStatus().equals(AppointmentStatus.RESERVED)) {
                AppointmentDTO dto = new AppointmentDTO();
                dto.setId(a.getId());
                dto.setDateOfAppointmentt(a.getDateOfAppointment().toString());
                dto.setMeetingTimee(a.getMeetingTime().toString());

                if (a.getDermatologist() != null) {

                    dto.setFirstName(a.getDermatologist().getFirstName());
                    dto.setLastName(a.getDermatologist().getLastName());
                    dto.setRole("DERMATOLOGIST");
                    dto.setType(AppointmentType.EXAMINATION.toString());
                } else {
                    dto.setFirstName(a.getPharmacist().getFirstName());
                    dto.setLastName(a.getPharmacist().getLastName());
                    dto.setRole("PHARMACIST");
                    dto.setType(AppointmentType.COUNCELING.toString());
                }

                dto.setDuration(a.getDuration());
                dto.setPrice(a.getPrice());

                myList.add(dto);
            }
        }

        return myList;
    }

    public boolean cancelAppointment(Long id, Long patientID) {

        boolean cancelled = false;

        Appointment a = appointmentRepository.findOneById(id);
        Patient p = patientRepository.findOneById(patientID);

        // checking dates

        Calendar myDate = Calendar.getInstance();

        Date dateOfApp = a.getDateOfAppointment();
        int hours = a.getMeetingTime().getHour();

        myDate.setTime(dateOfApp);
        myDate.set(Calendar.HOUR_OF_DAY, hours);

        Date printDate = myDate.getTime();

        System.out.println("MY DATE AND TIME OF APP:" + printDate);

        myDate.add(Calendar.HOUR_OF_DAY, -24);

        Date printDate1 = myDate.getTime();
        System.out.println("MY DATE -24:  " + printDate1);

        Date currentDate = Calendar.getInstance().getTime();

        if (a.getPatient().getId() == patientID) {
            if (currentDate.before(printDate1)) {

                a.setStatus(AppointmentStatus.FREE);
                System.out.println(a.getDermatologist().getFirstName());
                System.out.println(a.getDermatologist().getId());
                System.out.println(a.getPatient().getId());
                this.update(a);

                this.patientService.update(a.getPatient());

                cancelled = true;
            }
        }

        return cancelled;

    }

}

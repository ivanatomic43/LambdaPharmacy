package com.example.pharmacybackend.services;

import com.example.pharmacybackend.repository.PatientRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.AppointmentDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Appointment findById(long id) {
        return this.appointmentRepository.findById(id);
    }

    public List<Appointment> getAll() {
        return this.appointmentRepository.findAll();
    }

    // dermatologist
    public AppointmentDTO createAppointment(AppointmentDTO newApp) {

        System.out.println("PHID" + newApp.getPharmacyID());
        System.out.println("DERMID" + newApp.getDermatologistID());

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

        System.out.println("podaci za appointment: " + a.getId());

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
        System.out.println("PHARMACY ID U get predefined: " + id);
        List<Appointment> appList = pharmacy.getPharmacyAppointments();
        List<AppointmentDTO> retList = new ArrayList<>();

        for (Appointment a : appList) {
            if (a.getStatus().equals(AppointmentStatus.FREE)) {
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

}

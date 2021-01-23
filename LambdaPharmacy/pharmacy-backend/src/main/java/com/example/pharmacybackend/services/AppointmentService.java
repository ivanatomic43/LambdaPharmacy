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
import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.*;
import org.springframework.transaction.annotation.Transactional;

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
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    @Autowired
    private EndedAppointmentsRepository endedAppointmentRepository;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PharmacyService pharmacyService;

    public Appointment findById(long id) {
        return this.appointmentRepository.findById(id);
    }

    public List<Appointment> getAll() {
        return this.appointmentRepository.findAll();
    }

    @Transactional
    public Appointment update(Appointment ap) {
        return this.appointmentRepository.save(ap);
    }

    // dermatologist
    public AppointmentDTO createAppointment(AppointmentDTO newApp) {

        // checking if dermatologist is available
        EmployedDermatologist dermatologist = employedDermatologistRepository.findByDermId(newApp.getDermatologistID());
        Pharmacy pharmacy = pharmacyRepository.findOneById(newApp.getPharmacyID());

        LocalTime dermFrom = dermatologist.getWorkFrom();
        LocalTime dermTo = dermatologist.getWorkTo();

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

        this.update(a);
        // newApp.setId(a.getId());

        // adding a to dermatologist
        List<Appointment> reservedAppointments = dermatologist.getReservedAppointments();
        reservedAppointments.add(a);

        // adding a to pharmacy

        List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
        pharmacyAppointments.add(a);

        // save all
        dermatologistService.saveEmployed(dermatologist);
        // employedDermatologistRepository.save(dermatologist);
        pharmacyService.savePharmacy(pharmacy);

        // creating a dto
        String firstName = dermatologist.getDermatologist().getFirstName();
        String lastName = dermatologist.getDermatologist().getLastName();
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
        dto.setPharmacyName(a.getPharmacy().getName());
        // dto.setRating(rating);
        dto.setPrice(dermatologist.getPrice());

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
                dto.setFirstName(a.getDermatologist().getDermatologist().getFirstName());
                dto.setLastName(a.getDermatologist().getDermatologist().getLastName());
                dto.setMeetingTimee(a.getMeetingTime().toString());
                dto.setPharmacyName(a.getPharmacy().getName());
                // dto.setRating(a.getDermatologist().getRating());
                dto.setPrice(a.getPrice());

                retList.add(dto);
            }
        }

        return retList;
    }

    @Transactional
    public boolean reserveAppointment(Long id, Long userID) {

        boolean reserved = false;

        Appointment a = appointmentRepository.findOneById(id);
        Patient patient = patientRepository.findOneById(userID);

        a.setStatus(AppointmentStatus.RESERVED);
        this.update(a);

        appointmentRepository.setPatient(patient.getId(), a.getId());

        this.update(a);
        patientService.update(patient);

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

                    dto.setFirstName(a.getDermatologist().getDermatologist().getFirstName());
                    dto.setLastName(a.getDermatologist().getDermatologist().getLastName());
                    dto.setRole("DERMATOLOGIST");
                    dto.setType(AppointmentType.EXAMINATION.toString());
                    dto.setPrice(a.getDermatologist().getPrice());
                } else {
                    dto.setFirstName(a.getPharmacist().getFirstName());
                    dto.setLastName(a.getPharmacist().getLastName());
                    dto.setRole("PHARMACIST");
                    dto.setType(AppointmentType.COUNCELING.toString());
                    dto.setPrice(a.getPharmacist().getPrice());
                }

                dto.setDuration(a.getDuration());

                dto.setPharmacyName(a.getPharmacy().getName());

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
                System.out.println(a.getDermatologist().getDermatologist().getFirstName());
                System.out.println(a.getDermatologist().getDermatologist().getId());
                System.out.println(a.getPatient().getId());
                this.update(a);

                this.patientService.update(a.getPatient());

                cancelled = true;
            }
        }

        return cancelled;

    }

    @Transactional
    public boolean reserveCounceling(AppointmentDTO newApp, Long userID) {
        // insert validation
        boolean reserved = false;

        System.out.println(newApp.getDateOfAppointmentt());
        System.out.println(newApp.getMeetingTimee());
        System.out.println(newApp.getPharmacistID());
        System.out.println(newApp.getPharmacyID());

        Patient p = patientRepository.findOneById(userID);
        Appointment a = new Appointment();
        Pharmacy pharmacy = pharmacyRepository.findOneById(newApp.getPharmacyID());
        Pharmacist pharmacist = pharmacistRepository.findOneById(newApp.getPharmacistID());

        a.setDateOfAppointment(newApp.getDateOfAppointment());
        a.setMeetingTime(newApp.getMeetingTime());
        a.setType(AppointmentType.COUNCELING);
        a.setStatus(AppointmentStatus.RESERVED);
        a.setDuration(1);

        this.update(a);

        appointmentRepository.setPatient(p.getId(), a.getId());
        appointmentRepository.setPharmacist(pharmacist.getId(), a.getId());
        appointmentRepository.setPharmacy(pharmacy.getId(), a.getId());

        a.setPrice(pharmacist.getPrice());

        this.update(a);
        List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
        pharmacyAppointments.add(a);
        pharmacyService.savePharmacy(pharmacy);

        emailService.sendCouncelingReservationMail(p);

        reserved = true;

        return reserved;
    }

    public boolean endAppointment(Long id, Long userID) {

        boolean ended = false;

        Appointment appointment = appointmentRepository.findOneById(id);
        Patient p = patientRepository.findOneById(id);

        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.DONE);
            appointmentRepository.save(appointment);
            ended = true;
        }

        return ended;

    }

    public List<DermatologistDTO> getVisitedDoctors(Long userID) {

        List<DermatologistDTO> retList = new ArrayList<>();

        List<Appointment> endedAppointments = appointmentRepository.findByPatientId(userID);

        for (Appointment a : endedAppointments) {
            if (a.getStatus().equals(AppointmentStatus.DONE)) {
                if (a.getType().equals(AppointmentType.EXAMINATION)) { // u pitanju je pregled kod dermatologa

                    DermatologistDTO dto = new DermatologistDTO();
                    dto.setId(a.getDermatologist().getDermatologist().getId());
                    dto.setFirstName(a.getDermatologist().getDermatologist().getFirstName());
                    dto.setLastName(a.getDermatologist().getDermatologist().getLastName());
                    dto.setRole(a.getDermatologist().getDermatologist().getAuthority().getName());
                    dto.setRating(a.getDermatologist().getRating());

                    if (!retList.isEmpty()) {
                        for (DermatologistDTO t : retList) {
                            if (t.getId() != dto.getId()) {
                                retList.add(dto);
                            }
                        }
                    } else {
                        retList.add(dto);
                    }

                } else if (a.getType().equals(AppointmentType.COUNCELING)) {

                    DermatologistDTO dto = new DermatologistDTO();
                    dto.setId(a.getPharmacist().getId());
                    dto.setFirstName(a.getPharmacist().getFirstName());
                    dto.setLastName(a.getPharmacist().getLastName());
                    dto.setRole(a.getPharmacist().getAuthority().getName());
                    dto.setRating(a.getPharmacist().getRating());

                    if (!retList.contains(dto)) {
                        retList.add(dto);
                    }

                }

            }

        }
        return retList;
    }

    public List<AppointmentDTO> getEndedAppointments(Long userID) {

        List<AppointmentDTO> retList = new ArrayList<>();
        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment a : appointments) {
            if (a.getStatus().equals(AppointmentStatus.DONE)) {
                if (a.getPatient().getId() == userID) {

                    AppointmentDTO dto = new AppointmentDTO();
                    dto.setId(a.getId());
                    dto.setDateOfAppointmentt(a.getDateOfAppointment().toString());
                    dto.setMeetingTimee(a.getMeetingTime().toString());

                    if (a.getDermatologist() != null) {

                        dto.setFirstName(a.getDermatologist().getDermatologist().getFirstName());
                        dto.setLastName(a.getDermatologist().getDermatologist().getLastName());
                        dto.setRole("DERMATOLOGIST");
                        dto.setType(AppointmentType.EXAMINATION.toString());
                        dto.setPrice(a.getDermatologist().getPrice());
                    } else {

                        dto.setFirstName(a.getPharmacist().getFirstName());
                        dto.setLastName(a.getPharmacist().getLastName());
                        dto.setRole("PHARMACIST");
                        dto.setType(AppointmentType.COUNCELING.toString());
                        dto.setPrice(a.getPharmacist().getPrice());
                    }

                    dto.setDuration(a.getDuration());

                    dto.setPharmacyName(a.getPharmacy().getName());

                    if (!retList.contains(dto)) {
                        retList.add(dto);
                    }

                }
            }
        }

        return retList;

    }

    public List<PharmacyDTO> getVisitedPharmacies(Long userID) {

        List<PharmacyDTO> retList = new ArrayList<>();
        List<Appointment> endedAppointments = appointmentRepository.findAll();

        for (Appointment a : endedAppointments) {
            if (a.getStatus().equals(AppointmentStatus.DONE) && a.getPatient().getId() == userID) {

                PharmacyDTO dto = new PharmacyDTO();
                dto.setId(a.getPharmacy().getId());
                dto.setName(a.getPharmacy().getName());
                dto.setAddress(a.getPharmacy().getAddress());
                dto.setDescription(a.getPharmacy().getDescription());
                dto.setRating(a.getPharmacy().getRating());

                if (!retList.isEmpty()) {
                    for (PharmacyDTO t : retList) {
                        if (t.getId() != dto.getId()) {
                            retList.add(dto);
                        }
                    }
                } else {
                    retList.add(dto);
                }

            }

        }

        return retList;

    }

}

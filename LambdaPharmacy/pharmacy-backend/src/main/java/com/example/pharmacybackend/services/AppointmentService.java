package com.example.pharmacybackend.services;

import com.example.pharmacybackend.repository.PatientRepository;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.pharmacybackend.dto.AppointmentDTO;
import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.enumerations.AppointmentStatus;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.AppointmentLoyalty;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.Rating;
import com.example.pharmacybackend.repository.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    @Autowired
    private AppointmentLoyaltyRepository appLoyaltyRepository;

    @Autowired
    private DermatologistService dermatologistService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private RatingRepository ratingRepository;

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

    @Transactional
    public void updatePatient(Long patient_id, Long ap_id) {

        Appointment a = appointmentRepository.findOneById(ap_id);
        Patient p = patientRepository.findOneById(patient_id);
        a.setPatient(p);
        this.update(a);

    }

    // dermatologist
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AppointmentDTO createAppointment(AppointmentDTO newApp) {

        // checking if dermatologist is available
        List<EmployedDermatologist> dermatologistList = employedDermatologistRepository
                .findByDermId(newApp.getDermatologistID());
        Pharmacy pharmacy = pharmacyRepository.findOneById(newApp.getPharmacyID());
        List<EmployedDermatologist> freeDermatologists = new ArrayList<>();

        for (EmployedDermatologist dermatologist : dermatologistList) {
            if (dermatologist.getPharmacy().getId() == newApp.getPharmacyID()) {
                System.out.println("ID DERMATOLOGA KOJIS E PROVERAVA" + dermatologist.getDermatologist().getId());

                Date date = newApp.getDateOfAppointment();
                Date date1 = dermatologist.getDateFrom();
                Date date2 = dermatologist.getDateTo();

                if ((date.compareTo(date1) > 0 && date.compareTo(date2) < 0) || (date.compareTo(date1) == 0)
                        || (date.compareTo(date2) == 0)) {

                    LocalTime dermFrom = dermatologist.getWorkFrom();
                    LocalTime dermTo = dermatologist.getWorkTo();

                    LocalTime timeStart = newApp.getMeetingTime();
                    LocalTime timeEnd = timeStart.plusHours(newApp.getDuration());

                    System.out.println("TIME START:" + timeStart);
                    System.out.println("TIME END: " + timeEnd);
                    System.out.println("DERM FROM" + dermatologist.getWorkFrom());
                    System.out.println("DERM TO: " + dermatologist.getWorkTo());

                    if (timeStart.compareTo(dermFrom) < 0 || timeEnd.compareTo(dermTo) > 0) {
                        System.out.println("Time invalid");
                        return null;

                    } else {
                        System.out.println("USao u add free");
                        freeDermatologists.add(dermatologist);
                    }
                } else {
                    System.out.println("Not in date range...");
                    return null;
                }

            } else {
                System.out.println("Not in my pharmacy...");
            }

        }

        List<EmployedDermatologist> withoutDuplicates = new ArrayList<>();
        for (EmployedDermatologist dd : freeDermatologists) {
            if (!withoutDuplicates.contains(dd)) {
                System.out.println("USao u dodavanje dupl");
                withoutDuplicates.add(dd);
            }
        }
        // checking other appointments
        List<Appointment> allApp = appointmentRepository.findAll();
        if (!allApp.isEmpty()) {

            for (Appointment app : allApp) {
                for (EmployedDermatologist ddd : withoutDuplicates) {
                    if (app.getDermatologist().getId() == ddd.getDermatologist().getId()) { // ako ima sastanaka za
                                                                                            // njega

                        if (app.getDateOfAppointment().compareTo(newApp.getDateOfAppointment()) == 0) {

                            LocalTime appStart = app.getMeetingTime();
                            LocalTime appEnd = appStart.plusHours(app.getDuration());
                            LocalTime timeStart = newApp.getMeetingTime();

                            if (appStart.compareTo(newApp.getMeetingTime()) == 0 || timeStart.compareTo(appEnd) < 0) { // timeEnd
                                // -
                                // for
                                // new
                                // appointment
                                System.out.println("Time invalid, dermatologist is busy...");

                                return null;
                            } else {
                                // ako se id poslatog dermatologa poklapa sa slobodnim ddd onda napravi sastanak
                                // za njegaa
                                System.out.println("USAO u kreiranje app");
                                if (newApp.getDermatologistID() == ddd.getDermatologist().getId()) {
                                    Appointment a = new Appointment();
                                    a.setDateOfAppointment(newApp.getDateOfAppointment());
                                    a.setMeetingTime(newApp.getMeetingTime());
                                    a.setDuration(newApp.getDuration());
                                    a.setPrice(newApp.getPrice());
                                    a.setDermatologist(ddd);
                                    a.setPharmacy(pharmacy);
                                    a.setType(AppointmentType.EXAMINATION);
                                    a.setStatus(AppointmentStatus.FREE);

                                    this.update(a);
                                    // newApp.setId(a.getId());

                                    // adding a to dermatologist
                                    List<Appointment> reservedAppointments = ddd.getReservedAppointments();
                                    reservedAppointments.add(a);

                                    // adding a to pharmacy

                                    List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
                                    pharmacyAppointments.add(a);

                                    // save all
                                    dermatologistService.saveEmployed(ddd);
                                    // employedDermatologistRepository.save(dermatologist);
                                    pharmacyService.savePharmacy(pharmacy);

                                    // creating a dto
                                    String firstName = ddd.getDermatologist().getFirstName();
                                    String lastName = ddd.getDermatologist().getLastName();
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
                                    dto.setPrice(ddd.getPrice());

                                    return dto;
                                }

                            }

                        }
                    } else { // ne postoje kreirani sastanci za izabranog dermatologa, znaci slobodan je da
                             // se zakaze

                        System.out.println("USAO u kreiranje app za dermatologa koji nema sastanke");
                        if (newApp.getDermatologistID() == ddd.getDermatologist().getId()) {
                            Appointment a = new Appointment();
                            a.setDateOfAppointment(newApp.getDateOfAppointment());
                            a.setMeetingTime(newApp.getMeetingTime());
                            a.setDuration(newApp.getDuration());
                            a.setPrice(newApp.getPrice());
                            a.setDermatologist(ddd);
                            a.setPharmacy(pharmacy);
                            a.setType(AppointmentType.EXAMINATION);
                            a.setStatus(AppointmentStatus.FREE);

                            this.update(a);
                            // newApp.setId(a.getId());

                            // adding a to dermatologist
                            List<Appointment> reservedAppointments = ddd.getReservedAppointments();
                            reservedAppointments.add(a);

                            // adding a to pharmacy

                            List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
                            pharmacyAppointments.add(a);

                            // save all
                            dermatologistService.saveEmployed(ddd);
                            // employedDermatologistRepository.save(dermatologist);
                            pharmacyService.savePharmacy(pharmacy);

                            // creating a dto
                            String firstName = ddd.getDermatologist().getFirstName();
                            String lastName = ddd.getDermatologist().getLastName();
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
                            dto.setPrice(ddd.getPrice());

                            return dto;
                        }

                    }

                }

            }
        }

        return null;
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

    @Transactional // predefined
    public boolean reserveAppointment(Long id, Long userID) {

        boolean reserved = false;

        Appointment a = appointmentRepository.findOneById(id);
        Patient patient = patientRepository.findOneById(userID);

        List<AppointmentLoyalty> appList = appLoyaltyRepository.findAll();
        for (AppointmentLoyalty al : appList) {
            if (al.getType().equals(a.getType())) {
                a.setLoyaltyPoints(al.getPoints());
            }
        }

        a.setStatus(AppointmentStatus.RESERVED);
        this.update(a);

        this.updatePatient(patient.getId(), a.getId());

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
                    dto.setLoyaltyPoints(a.getLoyaltyPoints());
                } else {
                    dto.setFirstName(a.getPharmacist().getFirstName());
                    dto.setLastName(a.getPharmacist().getLastName());
                    dto.setRole("PHARMACIST");
                    dto.setType(AppointmentType.COUNCELING.toString());
                    dto.setPrice(a.getPharmacist().getPrice());
                    dto.setLoyaltyPoints(a.getLoyaltyPoints());
                }

                dto.setDuration(a.getDuration());

                dto.setPharmacyName(a.getPharmacy().getName());

                myList.add(dto);
            }
        }

        return myList;
    }

    @Transactional
    public boolean cancelAppointment(Long id, Long patientID) {

        boolean cancelled = false;

        Appointment a = appointmentRepository.findOneById(id);

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
    public void updatePharmacist(Long pharm_id, Long ap_id) {
        Appointment a = appointmentRepository.findOneById(ap_id);
        Pharmacist p = pharmacistRepository.findOneById(pharm_id);
        a.setPharmacist(p);
        this.update(a);
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
        a.setPatient(p);
        a.setPharmacy(pharmacy);
        a.setPrice(pharmacist.getPrice());

        this.update(a);

        this.updatePharmacist(pharmacist.getId(), a.getId());

        List<AppointmentLoyalty> appList = appLoyaltyRepository.findAll();
        for (AppointmentLoyalty al : appList) {
            if (al.getType().equals(a.getType())) {
                a.setLoyaltyPoints(al.getPoints());
            }
        }

        List<Appointment> pharmacyAppointments = pharmacy.getPharmacyAppointments();
        pharmacyAppointments.add(a);
        pharmacyService.savePharmacy(pharmacy);

        emailService.sendCouncelingReservationMail(p);

        reserved = true;

        return reserved;
    }

    @Transactional
    public boolean endAppointment(Long id, Long userID) {

        // loyalty points will be added after patient ends appointment
        boolean ended = false;

        Appointment appointment = appointmentRepository.findOneById(id);

        if (appointment != null) {
            appointment.setStatus(AppointmentStatus.DONE);
            Patient patient = appointment.getPatient();
            int p = patient.getLoyaltyPoints();
            int newp = p + appointment.getLoyaltyPoints();
            patient.setLoyaltyPoints(newp);
            appointmentRepository.save(appointment);
            patientService.update(patient);
            ended = true;
        }

        return ended;

    }

    public List<DermatologistDTO> getVisitedDoctors(Long userID) {

        List<DermatologistDTO> retList = new ArrayList<>();

        List<Appointment> endedAppointments = appointmentRepository.findByPatientId(userID);
        List<Rating> allRates = ratingRepository.findAll();

        for (Appointment a : endedAppointments) {
            if (a.getStatus().equals(AppointmentStatus.DONE)) {
                if (a.getType().equals(AppointmentType.EXAMINATION)) { // u pitanju je pregled kod dermatologa

                    DermatologistDTO dto = new DermatologistDTO();
                    dto.setId(a.getDermatologist().getDermatologist().getId());
                    dto.setFirstName(a.getDermatologist().getDermatologist().getFirstName());
                    dto.setLastName(a.getDermatologist().getDermatologist().getLastName());
                    dto.setRole(a.getDermatologist().getDermatologist().getAuthority().getName());
                    dto.setRating(a.getDermatologist().getDermatologist().getRating());

                    for (Rating r : allRates) {
                        if (r.getDermatologist() != null) {
                            if (r.getUser().getId() == userID && r.getDermatologist().getId() == dto.getId()) {
                                dto.setMyRate(r.getRate());
                            }
                        } else {
                            dto.setMyRate(0);
                        }
                    }

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

                    for (Rating r : allRates) {
                        if (r.getPharmacist() != null) {
                            if (r.getUser().getId() == userID && r.getPharmacist().getId() == dto.getId()) {
                                dto.setMyRate(r.getRate());
                            }
                        } else {
                            dto.setMyRate(0);
                        }
                    }

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
        List<Rating> allRates = ratingRepository.findAll();

        for (Appointment a : endedAppointments) {
            if (a.getStatus().equals(AppointmentStatus.DONE) && a.getPatient().getId() == userID) {

                PharmacyDTO dto = new PharmacyDTO();
                dto.setId(a.getPharmacy().getId());
                dto.setName(a.getPharmacy().getName());
                dto.setStreet(a.getPharmacy().getAdd().getStreet());
                dto.setCity(a.getPharmacy().getAdd().getCity());
                dto.setLatitude(a.getPharmacy().getAdd().getLatitude());
                dto.setLongitude(a.getPharmacy().getAdd().getLongitude());
                dto.setDescription(a.getPharmacy().getDescription());

                dto.setRating(a.getPharmacy().getRating());

                for (Rating r : allRates) {
                    if (r.getPharmacy() != null) {
                        if (r.getUser().getId() == userID && r.getPharmacy().getId() == dto.getId()) {
                            dto.setMyRate(r.getRate());
                        }
                    } else {
                        dto.setMyRate(0);
                    }
                }
                retList.add(dto);

            }

        }

        List<PharmacyDTO> newRetList = new ArrayList<>();
        for (PharmacyDTO dd : retList) {
            if (!newRetList.contains(dd)) {
                newRetList.add(dd);
            }
        }
        return newRetList;

    }

    public boolean deleteCounceling(Long id) {
        this.appointmentRepository.deleteCounceling(id);
        return true;
    }

    @Transactional
    public boolean cancelCounceling(Long id, Long patientID) {

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

                a.setPharmacy(null);
                a.setPharmacist(null);
                a.setPatient(null);
                appointmentRepository.delete(a);

                this.patientService.update(p);

                cancelled = true;
            }
        }

        return cancelled;

    }

}

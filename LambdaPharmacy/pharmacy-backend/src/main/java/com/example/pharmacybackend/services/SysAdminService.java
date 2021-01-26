package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.ComplaintDTO;
import com.example.pharmacybackend.dto.ReplyDTO;
import com.example.pharmacybackend.dto.VacationDTO;
import com.example.pharmacybackend.enumerations.ComplaintStatus;
import com.example.pharmacybackend.enumerations.VacationStatus;
import com.example.pharmacybackend.model.Complaint;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;

import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.model.Vacation;
import com.example.pharmacybackend.repository.ComplaintRepository;

import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PatientRepository;

import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.repository.VacationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysAdminService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Transactional
    public Complaint saveComplaint(Complaint c) {
        return this.complaintRepository.save(c);
    }

    @Transactional
    public Vacation saveVacation(Vacation v) {
        return this.vacationRepository.save(v);
    }

    public boolean sendComplaint(ComplaintDTO complaint, Long userID) {

        boolean created = false;

        Patient patient = patientRepository.findOneById(userID);
        Dermatologist dermatologist = new Dermatologist();

        List<EmployedDermatologist> derms = employedDermatologistRepository.findAll();
        for (EmployedDermatologist ed : derms) {
            if (ed.getDermatologist().getId() == complaint.getMyID()) {
                dermatologist = ed.getDermatologist();
            }
        }

        // Pharmacist pharmacist =
        // pharmacistRepository.findOneById(complaint.getMyID());
        // Pharmacy pharmacy = pharmacyRepository.findOneById(complaint.getMyID());

        Complaint c = new Complaint();
        c.setPatient(patient);
        c.setStatus(ComplaintStatus.PROCESSING);
        c.setText(complaint.getText());

        this.saveComplaint(c);
        created = true;
        return created;

    }

    public List<ComplaintDTO> getAllComplaints() {

        List<ComplaintDTO> retList = new ArrayList<>();

        List<Complaint> complaints = complaintRepository.findAll();

        for (Complaint c : complaints) {

            ComplaintDTO dto = new ComplaintDTO();

            dto.setId(c.getId());
            dto.setUserID(c.getPatient().getId());
            dto.setUserName(c.getPatient().getFirstName());
            dto.setUserSurname(c.getPatient().getLastName());
            dto.setStatus(c.getStatus().toString());
            dto.setText(c.getText());

            retList.add(dto);

        }
        return retList;
    }

    public boolean sendReply(ReplyDTO reply) {

        boolean done = false;

        Patient patient = patientRepository.findOneById(reply.getUserid());

        Complaint complaint = complaintRepository.findOneById(reply.getComid());

        if (complaint != null && patient != null) {
            complaint.setStatus(ComplaintStatus.REVIEWED);
            this.saveComplaint(complaint);
            emailService.sendReplyToComplainment(patient, reply.getAnswer());
            done = true;

        }
        return done;
    }

    public List<VacationDTO> getAllVacationRequests() {

        List<VacationDTO> retList = new ArrayList<>();

        List<Vacation> vacations = vacationRepository.findAll();

        for (Vacation v : vacations) {

            VacationDTO dto = new VacationDTO();
            dto.setId(v.getId());
            dto.setStatus(v.getStatus().toString());
            dto.setVacationFrom(v.getVacationFrom().toString());
            dto.setVacationTo(v.getVacationTo().toString());

            if (v.getDermatologist() != null) {
                dto.setName(v.getDermatologist().getDermatologist().getFirstName());
                dto.setSurname(v.getDermatologist().getDermatologist().getLastName());
                dto.setUserID(v.getDermatologist().getDermatologist().getId());
                dto.setRole(v.getDermatologist().getDermatologist().getAuthority().getName().toString());

            }

            if (v.getPharmacist() != null) {
                dto.setName(v.getPharmacist().getFirstName());
                dto.setSurname(v.getPharmacist().getLastName());
                dto.setUserID(v.getPharmacist().getId());
                dto.setRole(v.getPharmacist().getAuthority().getName().toString());
            }

            retList.add(dto);

        }

        return retList;

    }

    public boolean approveVacation(Long id, Long userid) {

        boolean approved = false;

        Vacation vacation = vacationRepository.findOneById(id);

        User user = userRepository.findOneById(userid);

        if (vacation != null && user != null) {
            vacation.setStatus(VacationStatus.APPROVED);
            emailService.sendApprovedVacationMail(user);
            vacationRepository.save(vacation);
            approved = true;
            return approved;
        }

        return false;
    }

    public boolean denyVacation(Long id, Long userid, String text) {

        boolean denied = false;

        Vacation vacation = vacationRepository.findOneById(id);

        User user = userRepository.findOneById(userid);

        if (vacation != null && user != null) {
            vacation.setStatus(VacationStatus.DENIED);
            emailService.sendDeniedVacationMail(user, text);
            vacationRepository.save(vacation);
            denied = true;
            return denied;
        }

        return false;

    }

}

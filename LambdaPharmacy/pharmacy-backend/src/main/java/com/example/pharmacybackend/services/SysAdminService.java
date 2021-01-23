package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.ComplaintDTO;
import com.example.pharmacybackend.dto.ReplyDTO;
import com.example.pharmacybackend.enumerations.ComplaintStatus;
import com.example.pharmacybackend.model.Complaint;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.repository.ComplaintRepository;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacistRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAdminService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

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

        Pharmacist pharmacist = pharmacistRepository.findOneById(complaint.getMyID());
        Pharmacy pharmacy = pharmacyRepository.findOneById(complaint.getMyID());

        Complaint c = new Complaint();
        c.setPatient(patient);
        c.setStatus(ComplaintStatus.PROCESSING);
        c.setText(complaint.getText());

        complaintRepository.save(c);
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
            complaintRepository.save(complaint);
            emailService.sendReplyToComplainment(patient, reply.getAnswer());
            done = true;

        }
        return done;
    }

}

package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.pharmacybackend.dto.CategoryDTO;
import com.example.pharmacybackend.dto.ComplaintDTO;
import com.example.pharmacybackend.dto.ReplyDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.dto.VacationDTO;
import com.example.pharmacybackend.enumerations.ComplaintStatus;
import com.example.pharmacybackend.enumerations.VacationStatus;
import com.example.pharmacybackend.model.AppointmentLoyalty;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Complaint;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.LoyaltyCategory;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Supplier;
import com.example.pharmacybackend.model.SystemAdministrator;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.model.Vacation;
import com.example.pharmacybackend.repository.AppointmentLoyaltyRepository;
import com.example.pharmacybackend.repository.ComplaintRepository;

import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.LoyaltyCategoryRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.SupplierRepository;
import com.example.pharmacybackend.repository.SysAdminRepository;
import com.example.pharmacybackend.repository.UserRepository;
import com.example.pharmacybackend.repository.VacationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private SysAdminRepository sysAdminRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private LoyaltyCategoryRepository categoryRepository;

    @Autowired
    private AppointmentLoyaltyRepository appointmentLoyaltyRepository;

    @Transactional
    public Complaint saveComplaint(Complaint c) {
        return this.complaintRepository.save(c);
    }

    @Transactional
    public Vacation saveVacation(Vacation v) {
        return this.vacationRepository.save(v);
    }

    @Transactional
    public void updateCategory(LoyaltyCategory cat) {
        this.categoryRepository.save(cat);
    }

    @Transactional
    public void updateAppLoy(AppointmentLoyalty cat) {
        this.appointmentLoyaltyRepository.save(cat);
    }

    public SystemAdministrator saveAdmin(SystemAdministrator sysAdmin) {
        return this.sysAdminRepository.save(sysAdmin);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return this.supplierRepository.save(supplier);
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

    @Transactional
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
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

    public SystemAdministrator registerSysAdmin(UserRequestDTO newUser) {

        SystemAdministrator a = new SystemAdministrator();

        a.setUsername(newUser.getUsername());
        a.setFirstName(newUser.getFirstName());
        a.setLastName(newUser.getLastName());
        a.setEmail(newUser.getEmail());
        a.setAddress(newUser.getAddress());
        a.setPhoneNumber(newUser.getPhoneNumber());
        a.setFirstLogin(true);
        a.setApproved(true);

        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
        a.setPassword(hashedPass);

        Authority role = new Authority();
        role = authorityService.findByName("ROLE_SYS_ADMIN");
        a.setAuthority(role);

        this.saveAdmin(a);

        return a;
    }

    public Supplier registerSupplier(UserRequestDTO newUser) {

        Supplier s = new Supplier();

        s.setUsername(newUser.getUsername());
        s.setFirstName(newUser.getFirstName());
        s.setLastName(newUser.getLastName());
        s.setEmail(newUser.getEmail());
        s.setAddress(newUser.getAddress());
        s.setPhoneNumber(newUser.getPhoneNumber());
        s.setFirstLogin(true);
        s.setApproved(true);

        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
        s.setPassword(hashedPass);

        Authority role = new Authority();
        role = authorityService.findByName("ROLE_SUPPLIER");
        s.setAuthority(role);

        this.saveSupplier(s);

        return s;
    }

    public List<CategoryDTO> getCategories() {

        List<CategoryDTO> retList = new ArrayList<>();
        List<LoyaltyCategory> allCat = categoryRepository.findAll();

        for (LoyaltyCategory c : allCat) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(c.getId());
            dto.setType(c.getType().toString());
            dto.setDiscount(c.getDiscount());
            dto.setPointsBorder(c.getPointsBorder());
            retList.add(dto);
        }

        return retList;
    }

    @Transactional
    public boolean editCategory(CategoryDTO cat) {

        List<LoyaltyCategory> list = categoryRepository.findAll();
        for (LoyaltyCategory c : list) {
            if (cat.getId() == c.getId()) {
                c.setDiscount(cat.getDiscount());
                c.setPointsBorder(cat.getPointsBorder());

                this.updateCategory(c);

                return true;
            }
        }
        return false;
    }

    public List<CategoryDTO> getAppLoyalty() {

        List<CategoryDTO> retList = new ArrayList<>();
        List<AppointmentLoyalty> allApp = appointmentLoyaltyRepository.findAll();

        for (AppointmentLoyalty c : allApp) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(c.getId());
            dto.setAppointmentType(c.getType().toString());
            dto.setAppPoints(c.getPoints());

            retList.add(dto);
        }

        return retList;
    }

    @Transactional
    public boolean editAppLoyalty(CategoryDTO cat) {

        List<AppointmentLoyalty> list = appointmentLoyaltyRepository.findAll();
        for (AppointmentLoyalty c : list) {
            if (cat.getId() == c.getId()) {

                c.setPoints(cat.getAppPoints());

                this.updateAppLoy(c);

                return true;
            }
        }
        return false;
    }

}

package com.example.pharmacybackend.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.enumerations.AppointmentType;
import com.example.pharmacybackend.model.Appointment;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Dermatologist;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.repository.DermatologistRepository;
import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private EmployedDermatologistRepository employedDermatologistRepository;

    public Dermatologist findById(long id) {
        return this.dermatologistRepository.findById(id);
    }

    public List<Dermatologist> findAll() {
        return this.dermatologistRepository.findAll();
    }

    @Transactional
    public Dermatologist save(Dermatologist dermatologist) {
        return this.dermatologistRepository.save(dermatologist);
    }

    @Transactional
    public EmployedDermatologist saveEmployed(EmployedDermatologist dermatologist) {
        return this.employedDermatologistRepository.save(dermatologist);
    }

    public Dermatologist registerDermatologist(UserRequestDTO newUser) {

        Dermatologist d = new Dermatologist();
        System.out.println(newUser.getUsername());

        d.setUsername(newUser.getUsername());
        d.setFirstName(newUser.getFirstName());
        d.setLastName(newUser.getLastName());
        d.setEmail(newUser.getEmail());
        d.setAddress(newUser.getAddress());
        d.setPhoneNumber(newUser.getPhoneNumber());
        d.setApproved(true);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
        String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
        d.setPassword(hashedPass);

        Authority role = new Authority();
        role = authorityService.findByName("ROLE_DERMATOLOGIST");
        d.setAuthority(role);

        this.save(d);

        return d;
    }

    // employ dermatologist
    public DermatologistDTO addDermatologist(DermatologistDTO dto, Long id) {

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);

        Dermatologist d = dermatologistRepository.findOneById(dto.getId());
        List<EmployedDermatologist> employed = employedDermatologistRepository.findAll();

        for (EmployedDermatologist ed : employed) { // checking if date and time match in another pharmacy where
                                                    // dermatologist is employed
            if (ed.getDermatologist().getId() == dto.getId()) {
                Date date1 = ed.getDateFrom();
                Date date2 = ed.getDateTo();
                Date date3 = dto.getDateFrom();
                Date date4 = dto.getDateTo();

                LocalTime time1 = ed.getWorkFrom();
                LocalTime time2 = ed.getWorkTo();
                LocalTime time3 = dto.getWorkFrom();
                LocalTime time4 = dto.getWorkTo();

                if ((date3.compareTo(date1) < 0 && date4.compareTo(date1) > 0)
                        || (date3.compareTo(date1) < 0 && date4.compareTo(date2) > 0)
                        || (date3.compareTo(date2) < 0 && date4.compareTo(date2) > 0)
                        || (date3.compareTo(date1) > 0 && date4.compareTo(date2) < 0)
                        || (date3.compareTo(date1) == 0 && date4.compareTo(date2) == 0)) {

                    if ((time3.isBefore(time1) && time4.isAfter(time1))
                            || (time3.isBefore(time2) && time4.isAfter(time2))
                            || (time3.isAfter(time1) && time4.isBefore(time2))
                            || (time3.equals(time1) && time4.equals(time2))) {
                        System.out.println("Dermatologist is employed in another pharmacy at exact time...");
                    } else {

                        EmployedDermatologist edd = new EmployedDermatologist();
                        edd.setDateFrom(dto.getDateFrom());
                        edd.setDateTo(dto.getDateTo());
                        edd.setWorkFrom(dto.getWorkFrom());
                        edd.setWorkTo(dto.getWorkTo());
                        edd.setPrice(dto.getPrice());
                        edd.setRating(0);
                        edd.setPharmacy(pharmacy);
                        edd.setDermatologist(d);

                        List<EmployedDermatologist> retDer = pharmacy.getDermatologists();

                        retDer.add(edd);

                        this.saveEmployed(edd);
                        pharmacyService.savePharmacy(pharmacy);
                        this.saveEmployed(edd);

                        DermatologistDTO retUser = new DermatologistDTO();
                        retUser.setId(d.getId());
                        retUser.setFirstName(d.getFirstName());
                        retUser.setLastName(d.getLastName());
                        retUser.setFrom(ed.getWorkFrom().toString());
                        retUser.setTo(ed.getWorkTo().toString());
                        retUser.setDateFromm(ed.getDateFrom().toString());
                        retUser.setDateToo(ed.getDateTo().toString());
                        retUser.setPrice(ed.getPrice());
                        retUser.setRating(ed.getRating());

                        return retUser;

                    }

                } else { // nor dates or time match

                    EmployedDermatologist edd = new EmployedDermatologist();
                    edd.setDateFrom(dto.getDateFrom());
                    edd.setDateTo(dto.getDateTo());
                    edd.setWorkFrom(dto.getWorkFrom());
                    edd.setWorkTo(dto.getWorkTo());
                    edd.setPrice(dto.getPrice());
                    edd.setRating(0);
                    edd.setPharmacy(pharmacy);
                    edd.setDermatologist(d);

                    List<EmployedDermatologist> retDer = pharmacy.getDermatologists();

                    retDer.add(edd);

                    this.saveEmployed(edd);
                    pharmacyService.savePharmacy(pharmacy);
                    this.saveEmployed(edd);

                    DermatologistDTO retUser = new DermatologistDTO();
                    retUser.setId(d.getId());
                    retUser.setFirstName(d.getFirstName());
                    retUser.setLastName(d.getLastName());
                    retUser.setFrom(ed.getWorkFrom().toString());
                    retUser.setTo(ed.getWorkTo().toString());
                    retUser.setDateFromm(ed.getDateFrom().toString());
                    retUser.setDateToo(ed.getDateTo().toString());
                    retUser.setPrice(ed.getPrice());
                    retUser.setRating(ed.getRating());

                    return retUser;

                }

            }

        }

        return null;

    }

    public List<DermatologistDTO> getAllDermForPharmacy(Long id) {

        List<DermatologistDTO> retList = new ArrayList<>();

        Pharmacy pharmacy = pharmacyRepository.findOneById(id);
        List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();

        for (EmployedDermatologist ed : derm) {
            if (ed.getPharmacy().getId() == id) {

                DermatologistDTO dto = new DermatologistDTO();
                dto.setId(ed.getDermatologist().getId());
                dto.setFirstName(ed.getDermatologist().getFirstName());
                dto.setLastName(ed.getDermatologist().getLastName());
                dto.setDateFromm(ed.getDateFrom().toString());
                dto.setDateToo(ed.getDateTo().toString());
                dto.setPrice(ed.getPrice());
                dto.setRating(ed.getRating());
                dto.setFrom(ed.getWorkFrom().toString());
                dto.setTo(ed.getWorkTo().toString());

                retList.add(dto);

            }
        }
        return retList;
    }

    @Transactional
    public boolean removeDermatologist(Long id, Long dermID) {

        boolean removed = false;
        System.out.println("DERM" + dermID);
        Pharmacy p = pharmacyRepository.findOneById(id);
        List<Appointment> allApp = p.getPharmacyAppointments();
        List<EmployedDermatologist> pharmDermatologist = p.getDermatologists();

        if (!allApp.isEmpty()) {
            for (Appointment a : allApp) {
                if (a.getType().equals(AppointmentType.EXAMINATION)) {
                    if (a.getDermatologist().getDermatologist().getId() == dermID) {
                        System.out.println("Dermatologist has reserved appointment...");
                        return removed;
                    } else {
                        System.out.println("USao u nije njegov sastanak");
                        List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();
                        for (EmployedDermatologist ed : derm) {
                            if (ed.getDermatologist().getId() == dermID) {
                                pharmDermatologist.remove(ed);
                                ed.setDermatologist(null);
                                ed.setPharmacy(null);
                                employedDermatologistRepository.delete(ed);
                                pharmacyService.savePharmacy(p);
                                removed = true;
                                return removed;
                            }
                        }

                    }
                } else {
                    System.out.println("Not examination, remove dermatologist");
                    List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();
                    for (EmployedDermatologist ed : derm) {
                        if (ed.getDermatologist().getId() == dermID) {
                            pharmDermatologist.remove(ed);
                            ed.setDermatologist(null);
                            ed.setPharmacy(null);
                            employedDermatologistRepository.delete(ed);
                            pharmacyService.savePharmacy(p);
                            removed = true;
                            return removed;
                        }
                    }
                }
            }
        } else {
            System.out.println("There is no appointments in this pharmacy, dermatologist removed");
            List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();
            for (EmployedDermatologist ed : derm) {
                if (ed.getDermatologist().getId() == dermID) {
                    pharmDermatologist.remove(ed);
                    ed.setDermatologist(null);
                    ed.setPharmacy(null);
                    employedDermatologistRepository.delete(ed);
                    pharmacyService.savePharmacy(p);
                    removed = true;
                    return removed;
                }
            }
        }

        return removed;
    }

    public List<DermatologistDTO> getDermatologistPatient() {

        List<DermatologistDTO> retList = new ArrayList<>();

        List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();
        List<Pharmacy> pharmList = pharmacyRepository.findAll();

        for (EmployedDermatologist d : derm) {

            DermatologistDTO dto = new DermatologistDTO();
            dto.setId(d.getDermatologist().getId());
            dto.setFirstName(d.getDermatologist().getFirstName());
            dto.setLastName(d.getDermatologist().getLastName());
            // dto.setUsername(d.getUsername());
            dto.setDateFromm(d.getDateFrom().toString());
            dto.setDateToo(d.getDateTo().toString());
            dto.setFrom(d.getWorkFrom().toString());
            dto.setTo(d.getWorkTo().toString());
            dto.setRating(d.getRating());
            dto.setPrice(d.getPrice());
            dto.setPharmacyName(d.getPharmacy().getName());

            retList.add(dto);

        }
        return retList;
    }

    // for pharmacyAdmin
    public List<DermatologistDTO> getDermatologist(Long id) {

        List<DermatologistDTO> retList = new ArrayList<>();
        List<Pharmacy> pharmacy = pharmacyRepository.findAll();
        List<EmployedDermatologist> dermatologists = employedDermatologistRepository.findAll();
        Long pharmacyID;

        for (Pharmacy p : pharmacy) {
            List<PharmacyAdministrator> admins = new ArrayList<>();
            admins = p.getPharmacyAdministrators();

            for (PharmacyAdministrator pa : admins) {
                if (pa.getId() == id) {
                    pharmacyID = p.getId();

                    for (EmployedDermatologist d : dermatologists) {
                        if (d.getPharmacy().getId() == p.getId()) {

                            DermatologistDTO dto = new DermatologistDTO();
                            dto.setId(d.getDermatologist().getId());
                            dto.setFirstName(d.getDermatologist().getFirstName());
                            dto.setLastName(d.getDermatologist().getLastName());
                            dto.setDateFromm(d.getDateFrom().toString());
                            dto.setDateToo(d.getDateTo().toString());
                            dto.setFrom(d.getWorkFrom().toString());
                            dto.setTo(d.getWorkTo().toString());
                            dto.setRating(d.getRating());
                            dto.setPrice(d.getPrice());
                            dto.setPharmacyName(d.getPharmacy().getName());

                            retList.add(dto);

                        }
                    }
                }

            }
        }

        return retList;
    }
}

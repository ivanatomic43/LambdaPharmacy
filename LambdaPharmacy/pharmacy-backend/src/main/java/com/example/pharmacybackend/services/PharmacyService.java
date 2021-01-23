package com.example.pharmacybackend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.PromotionRepository;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.PromotionDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;
import com.example.pharmacybackend.model.Promotion;

@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Autowired
	private PharmacyAdministratorRepository pharmacyAdministratorRepository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PatientService patientService;

	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepository.findAll();
	}

	@Transactional
	public Pharmacy savePharmacy(Pharmacy p) {
		return this.pharmacyRepository.save(p);
	}

	public Pharmacy findById(Long id) {
		Optional<Pharmacy> p = this.pharmacyRepository.findById(id);
		if (p.isPresent()) {
			Pharmacy pr = p.get();
			return pr;
		}

		return null;
	}

	public Pharmacy getPharmacy(Long id) {

		return this.pharmacyRepository.findOneById(id);
	}

	public List<PharmacyDTO> searchPharmacy(String name, String location) {
		System.out.println("ime" + name + "lokacija" + location);
		List<PharmacyDTO> retPha = new ArrayList<>();
		List<Pharmacy> pha = new ArrayList<>();
		List<Pharmacy> list = pharmacyRepository.findAll();

		for (Pharmacy p : list) {
			System.out.println(p.getName());
			if (!name.equals("") && location.equals("")) {
				if (p.getName().toLowerCase().equals(name.toLowerCase())) {
					System.out.println("usao u name" + p.getName());
					pha.add(p);
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			} else if (name.equals("") && !location.equals("")) {
				if (p.getAddress().toLowerCase().contains(location.toLowerCase())) {
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			} else if (!name.equals("") && !location.equals("")) {
				if (p.getName().toLowerCase().equals(name.toLowerCase())
						&& p.getAddress().toLowerCase().contains(location.toLowerCase())) {
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			}

		}

		return retPha;
	}

	public PharmacyDTO createPharmacy(PharmacyDTO pharmacy) {

		Pharmacy p = new Pharmacy();
		// System.out.println(pharmacy.getName() + pharmacy.getAddress() +
		// pharmacy.get);
		p.setName(pharmacy.getName());
		p.setAddress(pharmacy.getAddress());
		p.setDescription(pharmacy.getDescription());
		p.setRating(0);

		// setting pharmacy administrator
		System.out.println("ID ADMINA APOTeke" + pharmacy.getPharmacyAdministrator());
		PharmacyAdministrator pa = pharmacyAdministratorRepository.findOneById(pharmacy.getPharmacyAdministrator());
		List<PharmacyAdministrator> pharmAdmins = new ArrayList<>();

		pharmAdmins.add(pa);

		Pharmacy ph = this.savePharmacy(p);
		pa.setPharmacy(ph);

		PharmacyDTO ret = new PharmacyDTO();
		ret.setId(ph.getId());
		ret.setName(ph.getName());
		ret.setAddress(ph.getAddress());
		ret.setDescription(ph.getDescription());
		ret.setRating(ph.getRating());
		ret.setFirstName(pa.getFirstName());
		ret.setLastName(pa.getLastName());

		// PharmacyDTO ret = new PharmacyDTO(retPha);
		System.out.println(ret.getId());

		return ret;

	}

	public boolean registerAdmin(UserRequestDTO newUser) {

		boolean registred = false;
		PharmacyAdministrator d = new PharmacyAdministrator();
		System.out.println(newUser.getUsername());

		d.setUsername(newUser.getUsername());
		d.setFirstName(newUser.getFirstName());
		d.setLastName(newUser.getLastName());
		d.setEmail(newUser.getEmail());
		d.setAddress(newUser.getAddress());
		d.setPhoneNumber(newUser.getPhoneNumber());
		d.setFirstLogin(true);
		d.setApproved(true);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String salt = org.springframework.security.crypto.bcrypt.BCrypt.gensalt();
		String hashedPass = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(newUser.getPassword(), salt);
		d.setPassword(hashedPass);

		Authority role = new Authority();
		role = authorityService.findByName("ROLE_PHARMACY_ADMIN");
		d.setAuthority(role);

		Pharmacy p = pharmacyRepository.findOneById(newUser.getPharmacyID());
		List<PharmacyAdministrator> pharmAdmins = new ArrayList<>();
		pharmAdmins = p.getPharmacyAdministrators();
		pharmAdmins.add(d);

		pharmacyAdministratorRepository.save(d);
		this.savePharmacy(p);

		registred = true;
		return registred;
	}

	public List<UserDTO> getAdminsForPharmacy(Long pharmacyID) {

		List<UserDTO> retList = new ArrayList<>();

		Pharmacy pharm = pharmacyRepository.findOneById(pharmacyID);
		List<PharmacyAdministrator> admins = pharm.getPharmacyAdministrators();

		if (!admins.isEmpty()) {
			for (PharmacyAdministrator a : admins) {
				UserDTO dto = new UserDTO();
				dto.setId(a.getId());
				dto.setFirstName(a.getFirstName());
				dto.setLastName(a.getLastName());
				dto.setUsername(a.getUsername());
				dto.setAddress(a.getAddress());
				dto.setPassword(a.getPassword());
				dto.setPhoneNumber(a.getPhoneNumber());
				dto.setEmail(a.getEmail());

				retList.add(dto);

			}
		}

		return retList;

	}

	public boolean subscribeForNewsletter(Long id, Long userID) {

		Patient patient = patientRepository.findOneById(userID);
		List<Pharmacy> subPharmacy = new ArrayList<>();

		subPharmacy = patient.getSubscribedPharmacies();

		Pharmacy p = pharmacyRepository.findOneById(id);

		subPharmacy.add(p);

		patientService.update(patient);

		return true;

	}

	public boolean unsubscribeForNewsletter(Long id, Long userID) {

		Patient patient = patientRepository.findOneById(userID);
		List<Pharmacy> subPharmacy = new ArrayList<>();

		subPharmacy = patient.getSubscribedPharmacies();

		Pharmacy p = pharmacyRepository.findOneById(id);

		subPharmacy.remove(p);

		patientService.update(patient);

		return true;

	}

	public List<PharmacyDTO> getSubPharmacies(Long userID) {

		List<PharmacyDTO> retList = new ArrayList<>();

		Patient patient = patientRepository.findOneById(userID);

		List<Pharmacy> subb = patient.getSubscribedPharmacies();

		if (!subb.isEmpty()) {

			for (Pharmacy p : subb) {

				PharmacyDTO dto = new PharmacyDTO();
				dto.setId(p.getId());
				dto.setAddress(p.getAddress());
				dto.setDescription(p.getDescription());
				dto.setName(p.getName());

				retList.add(dto);
			}

		} else {
			return retList;
		}

		return retList;

	}

	public List<PromotionDTO> fetchAllPromotions(Long id) {

		List<PromotionDTO> retList = new ArrayList<>();

		List<Promotion> allProm = promotionRepository.findAll();

		for (Promotion p : allProm) {

			if (p.getPharmacy().getId() == id) {

				PromotionDTO dto = new PromotionDTO();
				dto.setId(p.getId());
				dto.setDescription(p.getDescription());
				dto.setDateFromm(p.getDateFrom().toString());
				dto.setDateToo(p.getDateTo().toString());

				retList.add(dto);

			}
		}

		return retList;
	}

	public PromotionDTO createPromotion(Long pharmacyID, PromotionDTO newProm) {

		Pharmacy p = pharmacyRepository.findOneById(pharmacyID);

		Promotion prom = new Promotion();
		prom.setDateFrom(newProm.getDateFrom());
		prom.setDateTo(newProm.getDateFrom());
		prom.setDescription(newProm.getDescription());
		prom.setPharmacy(p);

		promotionRepository.save(prom);

		// send e-mail to subscribers
		List<Patient> patients = patientRepository.findAll();

		for (Patient pat : patients) {
			List<Pharmacy> subPharm = new ArrayList<>();
			subPharm = pat.getSubscribedPharmacies();

			for (Pharmacy ph : subPharm) {
				if (ph.getId() == pharmacyID) {
					emailService.sendPromotionEmail(pat, prom, ph);
				}
			}
		}

		PromotionDTO ret = new PromotionDTO();
		ret.setId(prom.getId());
		ret.setDescription(prom.getDescription());
		ret.setDateFromm(prom.getDateFrom().toString());
		ret.setDateToo(prom.getDateTo().toString());
		return ret;

	}

	public PharmacyDTO getMyPharmacy(Long id) {

		PharmacyAdministrator admin = pharmacyAdministratorRepository.findOneById(id);

		List<Pharmacy> list = pharmacyRepository.findAll();

		for (Pharmacy p : list) {

			List<PharmacyAdministrator> admins = new ArrayList<>();
			admins = p.getPharmacyAdministrators();

			for (PharmacyAdministrator pm : admins) {

				if (pm.getId() == id) {
					PharmacyDTO dto = new PharmacyDTO();

					dto.setId(p.getId());
					dto.setName(p.getName());
					dto.setAddress(p.getAddress());
					dto.setDescription(p.getDescription());
					dto.setRating(p.getRating());

					return dto;
				}
			}

		}
		return null;
	}

}
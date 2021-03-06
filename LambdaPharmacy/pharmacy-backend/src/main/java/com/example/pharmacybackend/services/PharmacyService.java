package com.example.pharmacybackend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.pharmacybackend.repository.AddressRepository;
import com.example.pharmacybackend.repository.EmployedDermatologistRepository;
import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacistRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.repository.PromotionRepository;
import com.example.pharmacybackend.dto.DermatologistDTO;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.PromotionDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Address;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.EmployedDermatologist;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacist;
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
	private PharmacistRepository pharmacistRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private EmployedDermatologistRepository employedDermatologistRepository;

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

	@Transactional
	public void updatePharmacyRating(Long id, double rate) {
		Pharmacy p = pharmacyRepository.findOneById(id);
		p.setRating(rate);
		this.savePharmacy(p);
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

	@Transactional
	public List<PharmacyDTO> searchPharmacy(String name, String location) {

		List<PharmacyDTO> retPha = new ArrayList<>();
		List<Pharmacy> pha = new ArrayList<>();
		List<Pharmacy> list = pharmacyRepository.findAll();

		for (Pharmacy p : list) {

			if (!name.equals("") && location.equals("")) {

				if (p.getName().toLowerCase().equals(name.toLowerCase())) {

					pha.add(p);
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			} else if (name.equals("") && !location.equals("")) {
				String address = p.getAdd().getStreet() + " " + p.getAdd().getCity();
				if (address.toLowerCase().contains(location.toLowerCase())) {
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			} else if (!name.equals("") && !location.equals("")) {
				String address = p.getAdd().getStreet() + " " + p.getAdd().getCity();
				if (p.getName().toLowerCase().equals(name.toLowerCase())
						&& address.toLowerCase().contains(location.toLowerCase())) {
					PharmacyDTO dto = new PharmacyDTO(p);
					retPha.add(dto);
				}
			}

		}

		return retPha;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PharmacyDTO createPharmacy(PharmacyDTO pharmacy) {

		Pharmacy p = new Pharmacy();
		Address a = new Address();
		a.setStreet(pharmacy.getStreet());
		a.setCity(pharmacy.getCity());
		addressRepository.save(a);

		p.setName(pharmacy.getName());
		p.setAdd(a);
		p.setDescription(pharmacy.getDescription());
		p.setRating(0);

		/*
		 * setting pharmacy administrator
		 * 
		 * PharmacyAdministrator pa =
		 * pharmacyAdministratorRepository.findOneById(pharmacy.getPharmacyAdministrator
		 * ()); List<PharmacyAdministrator> pharmAdmins = new ArrayList<>();
		 * 
		 * List<Pharmacy> pharmacies = pharmacyRepository.findAll(); for (Pharmacy ph :
		 * pharmacies) { List<PharmacyAdministrator> admins = new ArrayList<>(); admins
		 * = ph.getPharmacyAdministrators();
		 * 
		 * for (PharmacyAdministrator pad : admins) { if (pad.getId() ==
		 * pharmacy.getPharmacyAdministrator()) {
		 * System.out.println("Administrator has been employed in another pharmacy...");
		 * return null; } } }
		 * 
		 * pharmAdmins.add(pa); pa.setPharmacy(ph);
		 */
		Pharmacy ph = this.savePharmacy(p);

		PharmacyDTO ret = new PharmacyDTO();
		ret.setId(ph.getId());
		ret.setName(ph.getName());
		ret.setStreet(ph.getAdd().getStreet());
		ret.setCity(ph.getAdd().getCity());
		ret.setDescription(ph.getDescription());
		ret.setRating(ph.getRating());
		// ret.setFirstName(pa.getFirstName());
		// ret.setLastName(pa.getLastName());

		return ret;

	}

	public boolean registerAdmin(UserRequestDTO newUser) {

		boolean registred = false;
		PharmacyAdministrator d = new PharmacyAdministrator();

		d.setUsername(newUser.getUsername());
		d.setFirstName(newUser.getFirstName());
		d.setLastName(newUser.getLastName());
		d.setEmail(newUser.getEmail());
		d.setAddress(newUser.getAddress());
		d.setPhoneNumber(newUser.getPhoneNumber());
		d.setFirstLogin(true);
		d.setApproved(true);

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
		d.setPharmacy(p);

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
				dto.setDescription(p.getDescription());
				dto.setName(p.getName());
				dto.setStreet(p.getAdd().getStreet());
				dto.setCity(p.getAdd().getCity());
				dto.setLatitude(p.getAdd().getLatitude());
				dto.setLongitude(p.getAdd().getLongitude());

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

		Pharmacy p = admin.getPharmacy();
		System.out.println(p.getName());
		PharmacyDTO dto = new PharmacyDTO();

		dto.setId(p.getId());
		dto.setName(p.getName());
		dto.setDescription(p.getDescription());
		dto.setRating(p.getRating());
		dto.setStreet(p.getAdd().getStreet());
		dto.setCity(p.getAdd().getCity());
		dto.setLatitude(p.getAdd().getLatitude());
		dto.setLongitude(p.getAdd().getLongitude());

		return dto;

	}

	public List<DermatologistDTO> getEmployedStaff(Long pharmacyID) {

		List<DermatologistDTO> retList = new ArrayList<>();

		List<EmployedDermatologist> derm = employedDermatologistRepository.findAll();

		for (EmployedDermatologist ed : derm) {
			if (ed.getPharmacy().getId() == pharmacyID) {

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
				dto.setRole(ed.getDermatologist().getAuthority().getName());

				retList.add(dto);

			}
		}

		List<Pharmacist> pharm = pharmacistRepository.findAll();
		for (Pharmacist p : pharm) {
			if (p.getPharmacy().getId() == pharmacyID) {

				DermatologistDTO dto = new DermatologistDTO();
				dto.setId(p.getId());
				dto.setFirstName(p.getFirstName());
				dto.setLastName(p.getLastName());
				dto.setPrice(p.getPrice());
				dto.setRating(p.getRating());
				dto.setRole(p.getAuthority().getName());

				retList.add(dto);
			}
		}

		return retList;

	}

}
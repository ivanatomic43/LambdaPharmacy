package com.example.pharmacybackend.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.expr.NewArray;

import com.example.pharmacybackend.repository.PatientRepository;
import com.example.pharmacybackend.repository.PharmacyAdministratorRepository;
import com.example.pharmacybackend.repository.PharmacyRepository;
import com.example.pharmacybackend.dto.PharmacyDTO;
import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.dto.UserRequestDTO;
import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.PharmacyAdministrator;

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

	public List<Pharmacy> getAllPharmacies() {
		return pharmacyRepository.findAll();
	}

	/*
	 * public void savePharmacy(Pharmacy createdPharmacy) { return
	 * this.pharmacyRepository.save(createdPharmacy); }
	 */
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

		pharmacyRepository.save(p);
		pa.setPharmacy(p);

		PharmacyDTO ret = new PharmacyDTO();
		ret.setId(p.getId());
		ret.setName(p.getName());
		ret.setAddress(p.getAddress());
		ret.setDescription(p.getDescription());
		ret.setRating(p.getRating());
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
		pharmacyRepository.save(p);

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

		patientRepository.save(patient);

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

}
package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.dto.UserRequestDTO;

import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Patient getPatient(Long id) {

		return this.patientRepository.findOneById(id);
	}

	public Patient findPatientByUsername(String username) {
		return this.patientRepository.findByUsername(username);
	}

	public Patient update(Patient p) {
		return this.patientRepository.save(p);
	}

	public Patient registerPatient(UserRequestDTO p) {

		Patient newPatient = new Patient();

		newPatient.setUsername(p.getUsername());
		newPatient.setPassword(passwordEncoder.encode(p.getPassword()));
		newPatient.setFirstName(p.getFirstName());
		newPatient.setLastName(p.getLastname());
		newPatient.setEmail(p.getEmail());
		newPatient.setAddress(p.getAddress());
		newPatient.setPhoneNumber(p.getPhoneNumber());

		newPatient.setApproved(false);

		return this.patientRepository.save(newPatient);

	}

}

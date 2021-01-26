package com.example.pharmacybackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pharmacybackend.dto.UserDTO;

import com.example.pharmacybackend.model.Patient;
import com.example.pharmacybackend.model.Pharmacy;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.PatientRepository;

import com.example.pharmacybackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public User findById(Long id) {

		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User findOneByUsername(String username) {

		// return userRepository.findOneByUsername(username);
		return userRepository.findOneByUsername(username);
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	@Transactional
	public void updateActivation(boolean active, Long id) {

		userRepository.updateActivation(active, id);

	}

	public UserDTO updateUser(UserDTO user) {

		List<User> allUsers = userRepository.findAll();

		for (User u : allUsers) {
			if (u.getId() == user.getId()) {

				if (!user.getFirstName().equals("")) {
					u.setFirstName(user.getFirstName());
				}

				if (!user.getLastName().equals("")) {
					u.setLastName(user.getLastName());
				}

				if (!user.getAddress().equals("")) {
					u.setAddress(user.getAddress());
				}

				if (!user.getPhoneNumber().equals("")) {
					u.setPhoneNumber(user.getPhoneNumber());
				}

				if (!user.getPassword().equals("")) {

					u.setPassword(passwordEncoder.encode(user.getPassword()));
				}

			}
			this.saveUser(u);
			return new UserDTO(u);
		}

		return null;
	}

	public List<UserDTO> getAllDermatologist() {

		List<User> userList = userRepository.findAll();
		List<UserDTO> retList = new ArrayList<>();

		for (User u : userList) {

			if (u.getAuthority().getName().toString().equals("ROLE_DERMATOLOGIST")) {
				UserDTO dto = new UserDTO(u);
				dto.setRole(u.getAuthority().getName());
				retList.add(dto);
			}
		}

		return retList;

	}

	public List<UserDTO> getAllPharmacists() {

		List<User> userList = userRepository.findAll();
		List<UserDTO> retList = new ArrayList<>();

		for (User u : userList) {
			System.out.println(u.getAuthority().getName());
			if (u.getAuthority().getName().toString().equals("ROLE_PHARMACIST")) {
				UserDTO dto = new UserDTO(u);
				dto.setRole(u.getAuthority().getName());
				retList.add(dto);
			}
		}

		return retList;

	}

	public List<UserDTO> getAdministrators() {

		List<User> userList = userRepository.findAll();
		List<UserDTO> retList = new ArrayList<>();

		for (User u : userList) {
			System.out.println(u.getAuthority().getName());
			if (u.getAuthority().getName().toString().equals("ROLE_PHARMACY_ADMIN")) {
				UserDTO dto = new UserDTO(u);
				dto.setRole(u.getAuthority().getName());
				retList.add(dto);
			}
		}

		return retList;

	}

	public boolean checkIfSub(Long id, Long userID) {

		boolean sub = false;

		Patient patient = patientRepository.findOneById(userID);

		List<Pharmacy> subPharm = patient.getSubscribedPharmacies();

		if (!subPharm.isEmpty()) {

			for (Pharmacy p : subPharm) {
				if (p.getId() == id) {
					return sub = true;
				}
			}
		} else {
			System.out.println("There is no subpharm...");
			return sub = false;
		}

		return sub;

	}

}

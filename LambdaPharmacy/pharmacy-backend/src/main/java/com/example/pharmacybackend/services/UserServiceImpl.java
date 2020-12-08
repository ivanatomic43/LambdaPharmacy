package com.example.pharmacybackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pharmacybackend.dto.UserDTO;
import com.example.pharmacybackend.model.User;
import com.example.pharmacybackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	public void updateActivation(boolean active, Long id) {

		User user = userRepository.findById(id).get();
		user.setApproved(active);
		userRepository.save(user);

	}

	public UserDTO updateUser(UserDTO user) {
		System.out.println("Usao u updateUser u service");
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
			userRepository.save(u);
			return new UserDTO(u);
		}

		return null;
	}

}

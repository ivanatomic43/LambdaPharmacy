package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findOneByUsername(String username) {
		// TODO Auto-generated method stub
		//return userRepository.findOneByUsername(username);
		return null;
	}

	
	
	
	
	
}

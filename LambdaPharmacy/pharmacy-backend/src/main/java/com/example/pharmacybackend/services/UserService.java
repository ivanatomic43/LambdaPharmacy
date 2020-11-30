package com.example.pharmacybackend.services;

import com.example.pharmacybackend.model.User;

public interface UserService {

	User findById(Long id);
	User findByUsername(String username);
	User findOneByUsername(String username);
	
}

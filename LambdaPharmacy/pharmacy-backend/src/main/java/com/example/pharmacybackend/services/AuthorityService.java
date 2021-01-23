package com.example.pharmacybackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Transactional
	public Authority save(Authority authority) {
		return authorityRepository.save(authority);
	}

	public Authority findByName(String name) {
		return authorityRepository.findOneByName(name);
	}

}

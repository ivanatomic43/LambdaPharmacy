package com.example.pharmacybackend.services;

import com.example.pharmacybackend.model.Authority;
import com.example.pharmacybackend.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<Authority> findById(Long id) {
    Authority auth = this.authorityRepository.getOne(id);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

@Override
public List<Authority> findByname(String name) {
	// TODO Auto-generated method stub
	 Authority auth = this.authorityRepository.findByName(name);
	    List<Authority> auths = new ArrayList<>();
	    auths.add(auth);
	    return auths;
}

 
}
package com.example.pharmacybackend.services;


import java.util.List;
import com.example.pharmacybackend.model.*;

public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}

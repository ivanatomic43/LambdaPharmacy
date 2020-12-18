package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmacybackend.model.Pharmacy;
import org.springframework.stereotype.Repository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

	List<Pharmacy> findAll();

	Pharmacy findOneById(Long id);
}

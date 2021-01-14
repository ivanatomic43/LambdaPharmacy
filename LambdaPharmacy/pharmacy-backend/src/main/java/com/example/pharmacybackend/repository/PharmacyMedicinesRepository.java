package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.PharmacyMedicine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyMedicinesRepository extends JpaRepository<PharmacyMedicine, Long> {

    PharmacyMedicine findOneById(Long id);

    List<PharmacyMedicine> findAll();

}

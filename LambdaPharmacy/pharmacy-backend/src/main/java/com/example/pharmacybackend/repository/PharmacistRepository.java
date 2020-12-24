package com.example.pharmacybackend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.pharmacybackend.model.Pharmacist;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    Pharmacist findById(long id);

    List<Pharmacist> findAll();

    Pharmacist findOneById(Long id);

}

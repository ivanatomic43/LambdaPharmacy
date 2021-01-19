package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.EmployedDermatologist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployedDermatologistRepository extends JpaRepository<EmployedDermatologist, Long> {

    List<EmployedDermatologist> findAll();

    EmployedDermatologist findOneById(Long id);

}

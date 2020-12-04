package com.example.pharmacybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmacybackend.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


	Patient findByEmail(String email);
    //List<Patient> findByStatus(Status status);
    Patient findOneById(Long id);
    Patient findById(long id);
    Patient findByUsername(String username);
	
	
}

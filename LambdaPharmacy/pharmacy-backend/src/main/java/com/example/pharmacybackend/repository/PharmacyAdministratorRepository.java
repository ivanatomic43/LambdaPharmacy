package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pharmacybackend.model.PharmacyAdministrator;

@Repository
public interface PharmacyAdministratorRepository extends JpaRepository<PharmacyAdministrator, Long> {

    PharmacyAdministrator findOneById(Long id);

    List<PharmacyAdministrator> findAll();

}

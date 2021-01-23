package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.EmployedDermatologist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployedDermatologistRepository extends JpaRepository<EmployedDermatologist, Long> {

    List<EmployedDermatologist> findAll();

    EmployedDermatologist findOneById(Long id);

    @Query("SELECT e FROM EmployedDermatologist e WHERE e.dermatologist.id=:id")
    EmployedDermatologist findByDermId(Long id);

}

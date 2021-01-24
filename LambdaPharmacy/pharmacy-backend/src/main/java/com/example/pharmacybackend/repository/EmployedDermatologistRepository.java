package com.example.pharmacybackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.pharmacybackend.model.EmployedDermatologist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployedDermatologistRepository extends JpaRepository<EmployedDermatologist, Long> {

    List<EmployedDermatologist> findAll();

    EmployedDermatologist findOneById(Long id);

    @Query("SELECT e FROM EmployedDermatologist e WHERE e.dermatologist.id=:id")
    List<EmployedDermatologist> findByDermId(Long id);

}

package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Vacation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findAll();

    Vacation findOneById(Long id);

}

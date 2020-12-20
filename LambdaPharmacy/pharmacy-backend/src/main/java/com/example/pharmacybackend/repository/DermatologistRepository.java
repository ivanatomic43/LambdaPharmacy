package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pharmacybackend.model.Dermatologist;

@Repository
public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {

    Dermatologist findById(long id);

    List<Dermatologist> findAll();

}

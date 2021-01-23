package com.example.pharmacybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.pharmacybackend.model.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Complaint findOneById(Long id);

    List<Complaint> findAll();

}

package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findOneById(Long id);

    List<Supplier> findAll();

}

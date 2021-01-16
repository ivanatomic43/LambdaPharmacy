package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Promotion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Promotion findOneById(Long id);

    List<Promotion> findAll();
}

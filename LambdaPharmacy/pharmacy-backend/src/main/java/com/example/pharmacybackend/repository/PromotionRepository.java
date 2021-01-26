package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Promotion findOneById(Long id);

    List<Promotion> findAll();
}

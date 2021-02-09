package com.example.pharmacybackend.repository;

import com.example.pharmacybackend.model.LoyaltyCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCategoryRepository extends JpaRepository<LoyaltyCategory, Long> {

    LoyaltyCategory findOneById(Long id);
}

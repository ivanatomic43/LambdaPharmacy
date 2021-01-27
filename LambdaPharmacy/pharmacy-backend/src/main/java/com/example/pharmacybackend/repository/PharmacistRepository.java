package com.example.pharmacybackend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.pharmacybackend.model.Pharmacist;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    Pharmacist findById(long id);

    List<Pharmacist> findAll();

    Pharmacist findOneById(Long id);

    @Modifying
    @Query(value = "update pharmacist set pharmacist.rating=?1 where pharmacist.id=?2", nativeQuery = true)
    public void updatePharmacistRating(double rating, Long pharmID);

}

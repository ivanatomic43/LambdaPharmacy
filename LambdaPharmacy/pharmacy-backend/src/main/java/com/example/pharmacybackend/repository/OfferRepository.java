package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAll();

}

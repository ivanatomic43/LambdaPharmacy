package com.example.pharmacybackend.repository;

import com.example.pharmacybackend.model.AppointmentLoyalty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentLoyaltyRepository extends JpaRepository<AppointmentLoyalty, Long> {

    AppointmentLoyalty findOneById(Long id);
}

package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.MedicineReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<MedicineReservation, Long> {

    MedicineReservation findOneById(Long id);

    List<MedicineReservation> findAll();

    @Query("SELECT r FROM MedicineReservation r WHERE r.patient.id=:id")
    List<MedicineReservation> findByPatientId(Long id);

}

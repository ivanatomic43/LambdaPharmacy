package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EndedAppointmentsRepository extends JpaRepository<Appointment, Long> {

    Appointment findOneById(Long id);

    List<Appointment> findAll();

    @Query("SELECT a FROM Appointment a WHERE a.patient.id=:id")
    List<Appointment> findByPatientId(Long id);

}

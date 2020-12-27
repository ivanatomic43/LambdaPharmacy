package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findById(long id);

    List<Appointment> findAll();

    Appointment findOneById(Long id);

    // @Query("SELECT a FROM APPOINTMENT a WHERE a.patient.id=:id")
    // List<Appointment> findByPatientId(Long id);

}

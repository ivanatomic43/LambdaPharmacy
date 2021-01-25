package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findById(long id);

    List<Appointment> findAll();

    Appointment findOneById(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id=:id")
    List<Appointment> findByPatientId(Long id);

    List<Appointment> getByPatientId(Long id);

    @Modifying
    @Query(value = "update appointment a set a.patient_id = ?1 where a.id = ?2", nativeQuery = true)
    public void setPatient(Long patientID, Long id);

    @Modifying
    @Query(value = "update appointment a set a.pharmacist_id = ?1 where a.id = ?2", nativeQuery = true)
    public void setPharmacist(Long pharmacistID, Long id);

    @Modifying
    @Query(value = "update appointment a set a.pharmacy_id = ?1 where a.id = ?2", nativeQuery = true)
    public void setPharmacy(Long pharmacyID, Long id);

    @Modifying
    @Query(value = "delete from appointment where a.id = ?1", nativeQuery = true)
    void deleteCounceling(Long id);
}

package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.MedicineReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<MedicineReservation, Long> {

    MedicineReservation findOneById(Long id);

    List<MedicineReservation> findAll();

    @Query("SELECT r FROM MedicineReservation r WHERE r.patient.id=:id")
    List<MedicineReservation> findByPatientId(Long id);

    @Modifying
    @Query(value = "update medicine_reservation r set r.medicine_id = ?1 where r.id = ?2", nativeQuery = true)
    public void updateResMed(Long medID, Long id);

    @Modifying
    @Query(value = "update medicine_reservation r set r.patient_id = ?1 where r.id = ?2", nativeQuery = true)
    public void updateResPatient(Long patientID, Long id);

    @Modifying
    @Query(value = "update medicine_reservation r set r.pharmacy_id = ?1 where r.id = ?2", nativeQuery = true)
    public void updateResPharmacy(Long pharmacyID, Long id);

}

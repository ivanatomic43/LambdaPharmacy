package com.example.pharmacybackend.repository;

import java.util.Date;
import java.util.List;

import com.example.pharmacybackend.model.PharmacyMedicine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyMedicinesRepository extends JpaRepository<PharmacyMedicine, Long> {

    PharmacyMedicine findOneById(Long id);

    List<PharmacyMedicine> findAll();

    @Modifying
    @Query(value = "update pharmacy_medicines pm set pm.medicine_id = ?1, pm.pharmacy_id = ?2, pm.price = ?3 where pm.id = ?4", nativeQuery = true)
    public void updateMedPrice(Long medID, Long pharmID, double price, Long id);

    @Modifying
    @Query(value = "update pharmacy_medicines pm set pm.medicine_id = ?1, pm.pharmacy_id = ?2, pm.price_lasts_to = ?3 where pm.id = ?4", nativeQuery = true)
    public void updateMedPriceLastsTo(Long medID, Long pharmID, Date priceLastsTo, Long id);

    @Modifying
    @Query(value = "delete from pharmacy_medicines where pharmacy_medicines.id=?1", nativeQuery = true)
    public void deleteMedicineFromPharmacy(Long id);
}

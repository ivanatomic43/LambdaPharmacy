package com.example.pharmacybackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.pharmacybackend.model.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Transactional
    @Query("select r.rate from Rating r where r.dermatologist.id = ?1")
    public List<Integer> dermatologistRates(Long dermID);

    @Transactional
    @Query("select r.rate from Rating r where r.pharmacist.id = ?1")
    public List<Integer> pharmacistRates(Long pharmID);

    @Transactional
    @Query("select r.rate from Rating r where r.medicine.id = ?1")
    public List<Integer> medicineRates(Long medicineID);

    @Transactional
    @Query("select r.rate from Rating r where r.pharmacy.id = ?1")
    public List<Integer> pharmacyRates(Long pharmacyID);

}

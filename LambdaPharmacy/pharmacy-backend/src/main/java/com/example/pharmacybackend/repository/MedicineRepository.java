package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.pharmacybackend.model.Medicine;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	List<Medicine> findAll();

	Medicine findOneById(Long id);

	@Modifying
	@Query(value = "update medicine set medicine.rating=?1 where medicine.id=?2", nativeQuery = true)
	public void updatePharmacistRating(double rating, Long medID);

}

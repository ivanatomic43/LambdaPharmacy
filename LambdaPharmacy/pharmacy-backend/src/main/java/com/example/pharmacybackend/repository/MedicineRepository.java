package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmacybackend.model.Medicine;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	List<Medicine> findAll();

	Medicine findOneById(Long id);

}

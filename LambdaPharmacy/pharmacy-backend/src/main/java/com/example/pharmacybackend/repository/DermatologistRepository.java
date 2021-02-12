package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pharmacybackend.model.Dermatologist;

@Repository
public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {

    // Dermatologist findById(Long id);

    List<Dermatologist> findAll();

    Dermatologist findOneById(Long id);

    /*
     * @Modifying
     * 
     * @Query(value =
     * "update dermatologist set dermatologist.rating=?1 where dermatologist.id=?2",
     * nativeQuery = true) public void updateDermatologistRating(double rating, Long
     * dermID);
     */
}

package com.example.pharmacybackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmacybackend.model.Image;
import com.example.pharmacybackend.model.Pharmacy;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image getByPharmacy(Pharmacy p);

}

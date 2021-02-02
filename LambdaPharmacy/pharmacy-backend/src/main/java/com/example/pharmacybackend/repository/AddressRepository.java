package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findOneById(Long id);

    List<Address> findAll();

}

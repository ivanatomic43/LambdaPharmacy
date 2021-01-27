package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.SystemAdministrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRepository extends JpaRepository<SystemAdministrator, Long> {

    SystemAdministrator findOneById(Long id);

    List<SystemAdministrator> findAll();

}

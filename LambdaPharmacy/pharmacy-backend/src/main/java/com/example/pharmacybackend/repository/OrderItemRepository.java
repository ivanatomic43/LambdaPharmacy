package com.example.pharmacybackend.repository;

import java.util.List;

import com.example.pharmacybackend.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAll();

}

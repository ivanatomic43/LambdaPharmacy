package com.example.pharmacybackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.example.pharmacybackend.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByUsername(String username);

	User findOneByUsername(String username);

	User findOneById(Long id);

	List<User> findAll();

	@Modifying
	@Query("update User u set u.approved = ?1 where u.id = ?2")
	public void updateActivation(boolean active, Long id);

}

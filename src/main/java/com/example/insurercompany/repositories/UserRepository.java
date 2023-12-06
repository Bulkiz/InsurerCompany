package com.example.insurercompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.insurercompany.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	boolean existsByUsernameOrEmail(String username, String email);
	
	boolean existsByUsernameAndPassword(String username, String password);
}

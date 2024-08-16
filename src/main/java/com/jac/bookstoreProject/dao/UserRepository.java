package com.jac.bookstoreProject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jac.bookstoreProject.model.CustomUser;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, String>{

	Optional<CustomUser> findByUsername(String username);
	
}

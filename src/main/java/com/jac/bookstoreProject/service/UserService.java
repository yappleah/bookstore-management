package com.jac.bookstoreProject.service;

import org.springframework.stereotype.Service;

import com.jac.bookstoreProject.model.CustomUser;

@Service
public interface UserService {
	CustomUser findByUsername(String username);
	
	void save(CustomUser customUser);
}

package com.jac.bookstoreProject.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.jac.bookstoreProject.dao.UserRepository;
import com.jac.bookstoreProject.model.CustomUser;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}
	
	public CustomUser findByUsername(String username) {
		Optional<CustomUser> foundUser = userRepository.findByUsername(username);
		
		CustomUser theUser = null;
		
		if (foundUser.isPresent()) {
			theUser = foundUser.get();
		}
		else {
			throw new RuntimeException("Did not find user");
		}
		
		return theUser;
	}
	
	@Override
	public void save(CustomUser theUser) {
		userRepository.save(theUser);
	}

}


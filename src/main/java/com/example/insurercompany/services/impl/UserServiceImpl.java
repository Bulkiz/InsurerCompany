package com.example.insurercompany.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.insurercompany.entities.User;
import com.example.insurercompany.exceptions.InvalidLoginInfo;
import com.example.insurercompany.exceptions.ObjectNameAlreadyExistsException;
import com.example.insurercompany.repositories.UserRepository;
import com.example.insurercompany.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void register(User user) {
		if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
			throw new ObjectNameAlreadyExistsException("Object with that name exists!");
		}
		userRepository.save(user);
	}

	@Override
	public void login(User user) {
		if (!userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword())) {
			throw new InvalidLoginInfo("There is no user with these credentials!");
		}
		
	}

}

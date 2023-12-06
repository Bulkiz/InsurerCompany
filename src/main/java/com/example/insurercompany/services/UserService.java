package com.example.insurercompany.services;

import com.example.insurercompany.entities.User;

public interface UserService {
	void register(User user);

	void login(User user);
}

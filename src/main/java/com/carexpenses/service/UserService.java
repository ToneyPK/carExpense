package com.carexpenses.service;

import com.carexpenses.entity.User;

public interface UserService {

	void registerUser(User theUser);
	User getUserByUsername(String username);
	boolean checkIfUserExists(User theUser);

//	public String getLoggedInUser();
	
}

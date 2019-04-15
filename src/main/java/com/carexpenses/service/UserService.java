package com.carexpenses.service;

import com.carexpenses.entity.User;

public interface UserService {

	public void registerUser(User theUser);
	public User getUserByUsername(String username);
	public boolean checkIfUserExists(User theUser);

//	public String getLoggedInUser();
	
}

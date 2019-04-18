package com.carexpenses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carexpenses.dao.UserRepository;
import com.carexpenses.dao.UserRoleRepository;
import com.carexpenses.entity.User;
import com.carexpenses.entity.UserRole;

/// TO DO:::::
/// NE INSERTUJE ROLES PRILIKOM REGISTRACIJE

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;



	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	@Transactional
	public void registerUser(User theUser) {
		theUser.setEnabled(true);		
		theUser.setPassword(passwordEncoder.encode( theUser.getPassword()));
		userRepository.save(theUser);
		UserRole userRole = new UserRole();
		userRole.setUsername(theUser.getUsername());
		userRole.setAuthority("ROLE_USER");
		userRoleRepository.save(userRole);
	}


	@Override
	public User getUserByUsername(String username) {
		Optional<User> usero = userRepository.findById(username);
		User user = usero.get();
		return user;
	}


	public boolean checkIfUserExists(User theUser) {
		boolean usersExists = userRepository.existsById(theUser.getUsername());
		boolean exists = false;
	
		if(usersExists) {
			exists = true;
		}

		return exists;
	}


}



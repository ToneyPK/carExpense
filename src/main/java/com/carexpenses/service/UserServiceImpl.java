package com.carexpenses.service;

import com.carexpenses.dao.UserRepository;
import com.carexpenses.dao.UserRoleRepository;
import com.carexpenses.entity.User;
import com.carexpenses.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
		Optional<User> userTemp = userRepository.findById(username);
		User user = userTemp.get();
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



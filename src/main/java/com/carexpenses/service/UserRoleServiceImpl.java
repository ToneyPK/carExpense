package com.carexpenses.service;

import com.carexpenses.dao.UserRoleRepository;
import com.carexpenses.entity.UserRole;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleRepository userRoleRepository;
	
	@Override
	public void saveRole(UserRole userRole) {
		userRoleRepository.save(userRole);

	}

}

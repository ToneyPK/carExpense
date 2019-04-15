package com.carexpenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carexpenses.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}

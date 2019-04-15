package com.carexpenses.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carexpenses.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}

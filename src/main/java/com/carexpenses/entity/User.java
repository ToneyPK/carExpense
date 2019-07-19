package com.carexpenses.entity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "username")
	@NotNull
	@Size(min=4,message="Please use 4 characters or more for your username." )
	private String username;
	
	@NotNull
	@Size(min=4,message="Please use 4 characters or more for your password." )
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		
	}

	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String password, boolean enabled, UserRole userRole) {
		this.password = password;
		this.enabled = enabled;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	

	
}

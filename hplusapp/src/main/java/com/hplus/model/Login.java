package com.hplus.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Login {
	@NotEmpty(message="please fill username")
	private String username;
	@NotEmpty(message="Please fill password")
	private String password;
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
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}
	
}

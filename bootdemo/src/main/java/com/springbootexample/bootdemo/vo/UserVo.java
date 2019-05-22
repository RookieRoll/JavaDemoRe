package com.springbootexample.bootdemo.vo;

import javax.validation.constraints.NotNull;

public class UserVo {
	@NotNull
	private String name;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String emailAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}

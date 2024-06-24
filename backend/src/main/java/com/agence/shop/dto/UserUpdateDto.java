package com.agence.shop.dto;

import java.io.Serializable;

import com.agence.shop.services.validation.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDto extends UserDto implements Serializable {
	private static final long serialVersionUID = 1L;


	private String password;
	
	UserUpdateDto() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}

package com.victornogueira.projectmongo.dto;

import java.io.Serializable;

import com.victornogueira.projectmongo.domain.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDto() {
	}

	public UserDto(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
	}
}
package com.victornogueira.projectmongo.dto;

import java.io.Serializable;

import com.victornogueira.projectmongo.domain.User;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AuthorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public AuthorDTO() {}

	public AuthorDTO(User user) {
		id = user.getId();
		name = user.getName();
	}
}
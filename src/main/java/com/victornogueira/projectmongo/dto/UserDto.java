package com.victornogueira.projectmongo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.domain.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	List<String> posts = new ArrayList<>();
	
	public UserDTO() {
	}

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		posts = user.getPosts()
				.stream()
				.map(Post::getId).toList();
	}
}
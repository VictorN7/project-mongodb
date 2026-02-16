package com.victornogueira.projectmongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victornogueira.projectmongo.domain.User;

@RestController
@RequestMapping("/users")
public class UserResource {

	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		User user1 = new User("1", "Victor", "victor@hotmail.com");
		User user2 = new User("2", "Maria", "maria@hotmail.com");

		List<User> list = Arrays.asList(user1, user2);
	
		return ResponseEntity.ok().body(list);
	}
	
}

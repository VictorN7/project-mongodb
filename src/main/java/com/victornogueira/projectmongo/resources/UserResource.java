package com.victornogueira.projectmongo.resources;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victornogueira.projectmongo.dto.UserDto;
import com.victornogueira.projectmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	private UserService userService;
	
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll(){
		return ResponseEntity.ok().body(userService.findAll().stream().map(x -> new UserDto(x)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(new UserDto(userService.findById(id)));
	}
	
}

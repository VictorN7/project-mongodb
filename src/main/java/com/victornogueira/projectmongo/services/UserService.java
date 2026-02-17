package com.victornogueira.projectmongo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victornogueira.projectmongo.domain.User;
import com.victornogueira.projectmongo.respositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		
		List<User> listUsers = userRepository.findAll();
		return listUsers;
	}

	public User findById(String id) {
		
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found id: "+id));
	}
	
}

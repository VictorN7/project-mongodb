package com.victornogueira.projectmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victornogueira.projectmongo.domain.User;
import com.victornogueira.projectmongo.respositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User user1 = new User(null,"Marcelo", "marcelo123@hotmail.com");
		User user2 = new User(null,"Lucas", "lucas@hotmail.com");
		User user3 = new User(null,"Matheus", "matheus2311@hotmail.com");
		User user4 = new User(null,"Marcia", "Marcia@hotmail.com");

		userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));
		
	}
}
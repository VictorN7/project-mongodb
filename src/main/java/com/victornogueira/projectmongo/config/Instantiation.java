package com.victornogueira.projectmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.domain.User;
import com.victornogueira.projectmongo.respositories.PostRepository;
import com.victornogueira.projectmongo.respositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User user1 = new User(null, "Marcelo", "marcelo123@hotmail.com");
		User user2 = new User(null, "Lucas", "lucas@hotmail.com");
		User user3 = new User(null, "Matheus", "matheus2311@hotmail.com");
		User user4 = new User(null, "Marcia", "Marcia@hotmail.com");

		Post post1 = new Post(null, sdf.parse("17/02/2026"), "Partiu viagem!", "Vou viajar para Pernambuco. Até mais!", user1);
		Post post2 = new Post(null, sdf.parse("20/02/2026"), "Novo projeto no ar","Acabei de subir um novo projeto em Java com Spring e MongoDB!", user2);
		Post post3 = new Post(null, sdf.parse("25/02/2026"), "Estudos a todo vapor","Revisando Spring Data, DTOs e boas práticas de arquitetura.",user1);
		Post post4 = new Post(null, sdf.parse("01/03/2026"), "Final de semana produtivo","Refatorei o código e melhorei a organização do backend.",user3);

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
		
	}
}
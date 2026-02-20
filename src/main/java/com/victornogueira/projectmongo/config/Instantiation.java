package com.victornogueira.projectmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.domain.User;
import com.victornogueira.projectmongo.dto.AuthorDTO;
import com.victornogueira.projectmongo.dto.CommentDTO;
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
		
		// Users para comentários
		
		User user5 = new User(null, "Camila", "camila@hotmail.com");
		User user6 = new User(null, "Luiza", "luiza11@hotmail.com");
		User user7 = new User(null, "Alessandro", "alessandro@hotmail.com");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6,user7));
		
		Post post1 = new Post(null, sdf.parse("17/02/2026"), "Partiu viagem!", "Vou viajar para Pernambuco. Até mais!", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("20/02/2026"), "Novo projeto no ar","Acabei de subir um novo projeto em Java com Spring e MongoDB!", new AuthorDTO(user2));
		Post post3 = new Post(null, sdf.parse("25/02/2026"), "Estudos a todo vapor","Revisando Spring Data, DTOs e boas práticas de arquitetura.",new AuthorDTO(user1));
		Post post4 = new Post(null, sdf.parse("01/03/2026"), "Final de semana produtivo","Refatorei o código e melhorei a organização do backend.",new AuthorDTO(user3));

		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
		
		user1.addAllPosts(Arrays.asList(post1, post3));
		user2.addPost(post2);
		user3.addPost(post4);
		
		CommentDTO comment1 = new CommentDTO("Boa viagem meu parceiro!", sdf.parse("17/02/2026"), new AuthorDTO(user7));
		CommentDTO comment2 = new CommentDTO("Aproveite bem rsrs", sdf.parse("17/02/2026"), new AuthorDTO(user6));
		CommentDTO comment3 = new CommentDTO("Ai simm! Quero ver esse projeto, hein!", sdf.parse("21/02/2026"), new AuthorDTO(user5));
		CommentDTO comment4 = new CommentDTO("E como foi esse desafio?", sdf.parse("01/03/2026"), new AuthorDTO(user6));
		CommentDTO comment5 = new CommentDTO("Bora marcar de estudar junto, mano? Estou nessa pegada de Spring Data e arquitetura limpa...", sdf.parse("26/02/2026"), new AuthorDTO(user7));
				
		post1.addComments(comment1);
		post1.addComments(comment2);
		post2.addComments(comment3);
		post3.addComments(comment4);
		post4.addComments(comment5);
		
		postRepository.saveAll(Arrays.asList(post1,post2,post3,post4));
		
		userRepository.saveAll(Arrays.asList(user1,user2,user3));
		
	}
}
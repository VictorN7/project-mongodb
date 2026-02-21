package com.victornogueira.projectmongo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.respositories.PostRepository;
import com.victornogueira.projectmongo.services.exception.ObjectNotFoundException;
import com.victornogueira.projectmongo.services.util.URL;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Post findById(String id) {
		return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findByTitle(String title) {
		
		List<Post> list = postRepository.findByTitleContainingIgnoreCase(URL.decodeParam(title));
		return list;
	}
}
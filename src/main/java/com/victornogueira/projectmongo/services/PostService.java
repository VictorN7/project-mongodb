package com.victornogueira.projectmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.respositories.PostRepository;
import com.victornogueira.projectmongo.services.exception.ObjectNotFoundException;
import com.victornogueira.projectmongo.resources.util.URL;

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
		return postRepository.searchTitle(URL.decodeParam(title));
	}

	public List<Post> fullSearch(String title, Date minDate, Date maxDate) {

		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(title, minDate, maxDate);
	}
}
package com.victornogueira.projectmongo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	private PostService postService;
	
	public PostResource(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> findPostById(@PathVariable String id) {
		return ResponseEntity.ok().body(postService.findById(id));
	}
}
package com.victornogueira.projectmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victornogueira.projectmongo.domain.Post;
import com.victornogueira.projectmongo.resources.util.URL;
import com.victornogueira.projectmongo.services.PostService;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostResource {
	
	private PostService postService;
	
	public PostResource(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> findPostById(@PathVariable String id) {
		return ResponseEntity.ok().body(postService.findById(id));
	}
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "txt", defaultValue = "") String title){
		return ResponseEntity.ok().body(postService.findByTitle(URL.decodeParam(title)));
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "txt", defaultValue = "") String title,
			@RequestParam(value = "minDate", defaultValue = "") String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "")String maxDate){
		
		return ResponseEntity.ok().body(postService.fullSearch(URL.decodeParam(title), 
				URL.convertDate(minDate, new Date(0L)) ,
				URL.convertDate(maxDate, new Date())));
	}
}
package com.victornogueira.projectmongo.respositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.victornogueira.projectmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	public List<Post> findByTitleContainingIgnoreCase(String title);
}

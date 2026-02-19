package com.victornogueira.projectmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;
	private String email;

	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>();
	
	public User() {
	}

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void updateName(String name) {
		if (name.equals(this.name)) {
			return;
		}
		this.name = name;
	}

	public void updateEmail(String email) {
		if (email.equals(this.email)) {
			return;
		}
		this.email = email;
	}
	
	public List<Post> getPosts() {
		return Collections.unmodifiableList(posts);
	}

	public void addPost(Post post) {
		
		if (post == null) {
			throw new IllegalArgumentException("Post cannot be null");
		}
		
		posts.add(post);
	}
	
	public void addAllPosts(List<Post> post) {
		
		if (post == null) {
			throw new IllegalArgumentException("Post cannot be null");
		}
		post.forEach(this::addPost);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User id: " + id + ", name: " + name + ", email: " + email ;
	}
}
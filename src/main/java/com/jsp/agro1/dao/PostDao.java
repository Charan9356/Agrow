package com.jsp.agro1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Post;
import com.jsp.agro1.repo.PostRepo;

@Repository
public class PostDao {
	
	@Autowired
	private PostRepo repo;
	
	public Post savePost(Post post) {
		return repo.save(post);
	}
	public Post fetchPostByID(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public Post deletePostByID(int id) {
		Optional<Post> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
	public Post updatePost(Post post) {
		Optional<Post> db = repo.findById(post.getId());
		if(db.isPresent()) {
			Post p = db.get();
			if(post.getImage()==null) {
				post.setImage(p.getImage());
			}
			if(post.getLikes()==0) {
				post.setLikes(p.getLikes());
			}
			if(post.getLocation()==null) {
				post.setLocation(p.getLocation());
			}
			if(post.getComments()==null) {
				post.setComments(p.getComments());
			}
			if(post.getCaption()==null) {
				post.setCaption(p.getCaption());
			}
			return repo.save(post);
		}
		else {
			return null;
		}
	}
	public List<Post> fetchAll(){
		return repo.findAll();
	}
}

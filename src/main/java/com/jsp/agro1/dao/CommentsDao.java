package com.jsp.agro1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Comments;
import com.jsp.agro1.repo.CommentsRepo;

@Repository
public class CommentsDao {
	
	@Autowired
	private CommentsRepo repo;
	
	public Comments saveComments(Comments comments) {
		return repo.save(comments);
	}
	public Comments fetchByID(int id) {
		Optional<Comments> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public Comments deleteCommentsByID(int id) {
		Optional<Comments> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
	public Comments updateComments(Comments comments) {
		Optional<Comments> db = repo.findById(comments.getId());
		if(db.isPresent()) {
			Comments c = db.get();
			if(comments.getComment()==null) {
				comments.setComment(c.getComment());
			}
			if(comments.getUser()==null) {
				comments.setUser(c.getUser());
			}
			return repo.save(comments);
		}
		else {
			return null;
		}
	}
}

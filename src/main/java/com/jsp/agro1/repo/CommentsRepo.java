package com.jsp.agro1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro1.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer>{

}

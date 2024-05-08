package com.jsp.agro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro1.entity.Comments;
import com.jsp.agro1.service.CommentsService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
public class CommentsController {
	
	@Autowired
	private CommentsService service;
	@PostMapping("/com")
	public ResponseEntity<ResponseStructure<Comments>> doComment(@RequestParam int pid,@RequestParam int uid,@RequestParam String comment){
		return service.doComment(pid, uid, comment);
	}
	@DeleteMapping("/com")
	public ResponseEntity<ResponseStructure<Comments>> deleteComment(@RequestParam int id){
		return service.deleteComment(id);
	}
}

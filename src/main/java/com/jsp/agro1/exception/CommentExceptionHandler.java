package com.jsp.agro1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.agro1.util.ResponseStructure;

@RestControllerAdvice
public class CommentExceptionHandler {
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<ResponseStructure<String>> commentNotFound(PostNotFound cnf){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData(cnf.getMsg());
		s.setMsg("comment not found");
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
}

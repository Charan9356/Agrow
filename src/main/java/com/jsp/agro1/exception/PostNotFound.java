package com.jsp.agro1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostNotFound extends RuntimeException{
	
	private String msg ="";
}

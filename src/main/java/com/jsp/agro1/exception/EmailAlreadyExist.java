package com.jsp.agro1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAlreadyExist  extends RuntimeException{
	
	private String msg = "";
}

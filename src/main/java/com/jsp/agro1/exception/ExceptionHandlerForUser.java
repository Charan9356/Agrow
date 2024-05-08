package com.jsp.agro1.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.agro1.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerForUser extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound unf){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData("no user for given id");
		s.setMsg(unf.getMsg());
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<ResponseStructure<String>> incorrectEmail(EmailAlreadyExist eae){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData("already registered");
		s.setMsg(eae.getMsg());
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailDoesntExist.class)
	public ResponseEntity<ResponseStructure<String>> incorrectEmail(EmailDoesntExist ede){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData("incorrect email");
		s.setMsg(ede.getMsg());
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PasswordIncorrect.class)
	public ResponseEntity<ResponseStructure<String>> passwordWrong(PasswordIncorrect pi){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData("incorrect password");
		s.setMsg(pi.getMsg());
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ResponseStructure<Map<String,String>>> handleValidation(MethodArgumentNotValidException ex){
//		Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//		ResponseStructure<Map<String, String>> s = new ResponseStructure<Map<String,String>>();
//		s.setData(errors);
//		s.setMsg("error");
//		s.setStatus(HttpStatus.BAD_REQUEST.value());
//		return new ResponseEntity<ResponseStructure<Map<String,String>>>(s,HttpStatus.BAD_REQUEST);
//	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			
		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();

		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);

		}
		structure.setMsg("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure<Object>();
		Map<String, String> map = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(field, message);

		}

		structure.setMsg("provide proper details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	}
	
}

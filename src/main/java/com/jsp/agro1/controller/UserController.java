package com.jsp.agro1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro1.entity.User;
import com.jsp.agro1.service.UserService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET ,RequestMethod.PUT,RequestMethod.DELETE })
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) throws Exception{
		return service.saveUser(user);
	}
	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<User>> fetchUserById(@RequestParam int id){
		return service.fetchByID(id);
	}
	@DeleteMapping("/user")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id){
		return service.deleteUser(id);
	}
	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email,@RequestParam String pwd){
		return service.loginUser(email, pwd);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return service.fetchAll();
	}
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return service.generateOtp(email);
	}
	@PutMapping("/updatepwd")
	public ResponseEntity<ResponseStructure<User>> updatePassword(@RequestParam String email,@RequestParam String pwd) throws Exception{
		return service.updatePassword(email, pwd);
	}
}

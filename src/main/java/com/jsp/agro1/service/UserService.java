package com.jsp.agro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.EmailAlreadyExist;
import com.jsp.agro1.exception.EmailDoesntExist;
import com.jsp.agro1.exception.PasswordIncorrect;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.repo.UserRepo;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserRepo repo;
	
	
	@Autowired
	private JavaMailSenderImpl javMailSenderImpl;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) throws Exception{
		User db = repo.fetchEmail(user.getEmail());
		if(db==null) {
			User db1 = dao.saveUser(user);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom("sakhamuricharanraju@gmail.com");
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setText("Thank you for your registration details are mentioned below \n"+user);
			simpleMailMessage.setSubject("successfully registered");
			javMailSenderImpl.send(simpleMailMessage);
		ResponseStructure<User> s = new ResponseStructure<User>();
		s.setData(db1);
		s.setMsg("user saved successfully");
		s.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.OK);
		}
		else {
			throw new EmailAlreadyExist(user.getEmail()+" is already register");
		}
	}
	public ResponseEntity<ResponseStructure<User>> fetchByID(int id){
		User db = dao.fetchByID(id);
		if(db!=null) {
		ResponseStructure<User> s = new ResponseStructure<User>();
		s.setData(db);
		s.setMsg("user found successfully");
		s.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("user not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		User db = dao.deleteUserByID(id);
		if(db!=null) {
			ResponseStructure<User> s = new ResponseStructure<User>();
			s.setData(db);
			s.setMsg("user account deleted");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("user not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		User db = dao.fetchByID(user.getId());
		if(db!=null) {
			ResponseStructure<User> s = new ResponseStructure<User>();
			s.setData(dao.updateUser(user));
			s.setMsg("user details updated");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.FOUND);
		}
		else {
			throw new UserNotFound("user not found for your search:"+user.getId());
		}
	}
	public ResponseEntity<ResponseStructure<User>> loginUser(String email,String pwd){
		try {
			User db = repo.fetchEmail(email);
				if(db.getPwd().equals(pwd)) {
					ResponseStructure<User> s = new ResponseStructure<User>();
					s.setData(db);
					s.setMsg("login success");
					s.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.OK);
				}
				else {
					throw new PasswordIncorrect("password is wrong");
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailDoesntExist(email+"does not exist");
		}
	}
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		ResponseStructure<List<User>> s = new ResponseStructure<List<User>>();
		List<User> list = dao.fetchAll();
		s.setData(list);
		s.setMsg("users founded successfully");
		s.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(s,HttpStatus.FOUND);
	}
	public ResponseEntity<ResponseStructure<Integer>> generateOtp(String email){
		int otp =0;
		for (int i = 0; i < 4; i++) {
			otp = otp*10+((int)(Math.random()*10));
		}
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("sakhamuricharanraju@gmail.com");
		simpleMailMessage.setTo(email);
		simpleMailMessage.setText("otp for resetting pwd is:"+otp);
		simpleMailMessage.setSubject("otp verification");
		javMailSenderImpl.send(simpleMailMessage);
		
		ResponseStructure<Integer> s = new ResponseStructure<Integer>();
		s.setData(otp);
		s.setMsg("otp sent successfully");
		s.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Integer>>(s,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<User>> updatePassword(String email,String pwd) throws Exception{
		User udb = dao.updatePassword(email, pwd);
		if(udb!=null) {
			ResponseStructure<User> s = new ResponseStructure<User>();
			s.setData(udb);
			s.setMsg("found successfully");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.OK);
		}
		else {
			throw new UserNotFound("user not found");
		}
	}
}

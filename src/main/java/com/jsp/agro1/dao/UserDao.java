package com.jsp.agro1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Image;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	public User fetchByID(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public User deleteUserByID(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		if(db.isPresent()) {
			User u = db.get();
			if(user.getFirstName()==null) {
				user.setFirstName(u.getFirstName());
			}
			if(user.getLastName()==null) {
				user.setLastName(u.getLastName());
			}
			if(user.getPhone()==0) {
				user.setPhone(u.getPhone());
			}
			if(user.getPwd()==null) {
				user.setPwd(u.getPwd());
			}
			if(user.getEmail()==null) {
				user.setEmail(u.getEmail());
			}
			if(user.getGender()==null) {
				user.setGender(u.getGender());
			}
			if(user.getAge()==0) {
				user.setAge(u.getAge());
			}
			if(user.getType()==null) {
				user.setType(u.getType());
			}
			if(user.getAddress()==null) {
				user.setAddress(u.getAddress());
			}
			return repo.save(user);
		}
		else {
			return null;
		}
	}
	public List<User> fetchAll(){
		return repo.findAll();
	}
	public User fetchUserByImage(Image image) {
		return repo.fetchUserByImage(image);
	}
	public User updatePassword(String email,String pwd) throws Exception {
		User u = repo.fetchEmail(email);
		if(u!=null) {
			u.setPwd(pwd);
			return saveUser(u);
		}
		return null;
	}
}

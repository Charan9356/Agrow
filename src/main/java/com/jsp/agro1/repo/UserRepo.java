package com.jsp.agro1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro1.entity.Image;
import com.jsp.agro1.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("select a from User a where a.email=?1")
	public abstract User fetchEmail(String email) throws Exception;
	
	@Query("select a from User a where a.image=?1")
	public abstract User fetchUserByImage(Image image);
}

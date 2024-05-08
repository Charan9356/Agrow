package com.jsp.agro1.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro1.dao.ImageDao;
import com.jsp.agro1.dao.PostDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.Image;
import com.jsp.agro1.entity.Post;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.PostNotFound;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class PostService {
	
	@Autowired
	private PostDao pdao;
	@Autowired
	private ImageDao idao;
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Post>> savePost(int id,MultipartFile file,String caption,String location) throws Exception{
		User user = udao.fetchByID(id);
		if(user!=null) {
			Image i = new Image();
			i.setData(file.getBytes());
			i.setName(file.getOriginalFilename());
			idao.saveImage(i);
			Post p = new Post();
			p.setImage(i);
			p.setDate(Date.valueOf(LocalDate.now()));
			p.setCaption(caption);
			p.setLocation(location);
			Post post = pdao.savePost(p);
			List<Post> pl = new ArrayList<Post>();
			pl.add(post);
			pl.addAll(user.getPost());
			user.setPost(pl);
			udao.updateUser(user);
			ResponseStructure<Post> s = new ResponseStructure<Post>();
			s.setData(post);
			s.setMsg("posted successfully");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>	(s,HttpStatus.OK);
		}
		else {
			throw new UserNotFound("user not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Post>> fetchByID(int id){
		Post db = pdao.fetchPostByID(id);
		if(db!=null) {
		ResponseStructure<Post> s = new ResponseStructure<Post>();
		s.setData(db);
		s.setMsg("post found successfully");
		s.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Post>>(s,HttpStatus.FOUND);
		}
		else {
			throw new PostNotFound("user not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Post>> deletePost(int id){
			Post post = pdao.fetchPostByID(id);
		if(post!=null) {
			List<User> users = udao.fetchAll() ;
			for(User u : users) {
				List<Post> posts = u.getPost();
				if(posts.contains(post)) {
					posts.remove(post);
					udao.updateUser(u);
					pdao.deletePostByID(id);
					break;
				}
			}
			ResponseStructure<Post> s = new ResponseStructure<Post>();
			s.setData(post);
			s.setMsg("post deleted");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Post>>(s,HttpStatus.OK);
		}
		else {
			throw new PostNotFound("post not found for your search:"+id);
		}
	}
}

package com.jsp.agro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro1.entity.Post;
import com.jsp.agro1.service.PostService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
public class PostController {
	@Autowired
	private PostService service;
	
	@PostMapping("/ps")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int id,@RequestParam String location,@RequestParam String caption,@RequestParam ("image") MultipartFile file) throws Exception{
		return service.savePost(id, file, caption, location);
	}
	@GetMapping("/ps")
	public ResponseEntity<ResponseStructure<Post>> fetchById(@RequestParam int id){
		return service.fetchByID(id);
	}
	@DeleteMapping("/ps")
	public ResponseEntity<ResponseStructure<Post>> deleteImage(@RequestParam int id){
		return service.deletePost(id);
	}
}

package com.jsp.agro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro1.entity.Image;
import com.jsp.agro1.service.ImageService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService service;
	
//	@Autowired
//	private ImageDao dao;
	
	@PostMapping("/ui")
	public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam int id,@RequestParam ("image") MultipartFile file) throws Exception{
		Image i = new Image();
		i.setName(file.getOriginalFilename());
		i.setData(file.getBytes());
		return service.uploadImage(id,file);
	}
	@GetMapping("/i")
	public ResponseEntity<byte[]> fetchImageById(@RequestParam int id){
		return service.fetchByID(id);
	}
	@DeleteMapping("/i")
	public ResponseEntity<ResponseStructure<Image>> deleteImage(@RequestParam int id){
		return service.deleteImage(id);
	}
	@PutMapping("/i")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam("image") MultipartFile file,@RequestParam int id) throws Exception{
		return service.updateImage(id,file);
	}
//	@GetMapping("/image")
//    public ResponseEntity<byte[]> getImage(@RequestParam int id) {
//        byte[] imageBytes = dao.fetchImageByID(id).getData();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//    }

}

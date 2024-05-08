package com.jsp.agro1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro1.dao.ImageDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.Image;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class ImageService {
	@Autowired
	private ImageDao dao;
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Image>> uploadImage(int id,MultipartFile file) throws Exception{
		User user = udao.fetchByID(id);
		if(user!=null) {
			if(user.getImage()==null) {
				Image i = new Image();
				i.setData(file.getBytes());
				i.setName(file.getOriginalFilename());
				Image idb = dao.saveImage(i);
				user.setImage(i);
				udao.updateUser(user);
				ResponseStructure<Image> s = new ResponseStructure<Image>();
				s.setData(idb);
				s.setMsg("image uploaded success");
				s.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Image>>(s,HttpStatus.OK);
			}
			else {
				return updateImage(user.getImage().getId(),file);
			}
		}
		else {
			throw new UserNotFound("user not found for your search"+id);
		}
	}
	public ResponseEntity<byte[]> fetchByID(int id){
		Image db = dao.fetchImageByID(id);
		if(db!=null) {
//		ResponseStructure<Image> s = new ResponseStructure<Image>();
		
		 byte[] imageBytes = dao.fetchImageByID(id).getData();
	     	HttpHeaders headers = new HttpHeaders();
	      	headers.setContentType(MediaType.IMAGE_JPEG);
//	      	s.setData(db);
//			s.setMsg("image found successfully");
//			s.setStatus(HttpStatus.OK.value());
	        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
		}
		else {
			throw new UserNotFound("user not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Image>> deleteImage(int id){
		Image db = dao.fetchImageByID(id);
			if(db!=null) {
				User udb = udao.fetchUserByImage(db);
				udb.setImage(null);
				udao.updateUser(udb);
				dao.deleteImageByID(id);
			ResponseStructure<Image> s = new ResponseStructure<Image>();
			s.setData(db);
			s.setMsg("image deleted");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(s,HttpStatus.FOUND);
		}
			else {
				throw new UserNotFound("user not found for:"+id);
			}
	}
	public ResponseEntity<ResponseStructure<Image>> updateImage(int id ,MultipartFile file) throws Exception{
		Image db = dao.fetchImageByID(id);
		if(db!=null) {
			db.setData(file.getBytes());
			db.setName(file.getOriginalFilename());
			Image i = dao.updateImage(db);
			ResponseStructure<Image> s = new ResponseStructure<Image>();
			s.setData(i);
			s.setMsg("image updated");
			s.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Image>>(s,HttpStatus.ACCEPTED);
		}
		else {
			throw new UserNotFound("image not found for your search:"+id);
		}
	}
	
}

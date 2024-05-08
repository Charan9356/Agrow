package com.jsp.agro1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Image;
import com.jsp.agro1.repo.ImageRepo;

@Repository
public class ImageDao {
	
	@Autowired
	private ImageRepo repo;
	
	public Image saveImage(Image image) {
		return repo.save(image);
	}
	public Image fetchImageByID(int id) {
		Optional<Image> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public Image deleteImageByID(int id) {
		Optional<Image> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
	public Image updateImage(Image image) {
		Optional<Image> db = repo.findById(image.getId());
		if(db.isPresent()) {
			Image i = db.get();
			if(image.getName()==null) {
				image.setName(i.getName());
			}
			if(image.getData()==null) {
				image.setData(i.getData());
			}
			return repo.save(image);
		}
		else {
			return null;
		}
	}
}


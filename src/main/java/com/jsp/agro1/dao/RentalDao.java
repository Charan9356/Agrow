package com.jsp.agro1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Rental;
import com.jsp.agro1.repo.RentalRepo;

@Repository
public class RentalDao {
	@Autowired
	private RentalRepo repo;
	
	public Rental saveRental(Rental rental) {
		return repo.save(rental);
	}
}

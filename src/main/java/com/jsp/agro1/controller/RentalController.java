package com.jsp.agro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro1.entity.Rental;
import com.jsp.agro1.service.RentalService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
public class RentalController {
	
	@Autowired
	private RentalService service;
	
	@PostMapping("/r")
	public ResponseEntity<ResponseStructure<Rental>> saveRental(@RequestParam int eid,@RequestParam int uid, @RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate){
		return service.saveRental(eid, uid, startdate, enddate);
	}
}

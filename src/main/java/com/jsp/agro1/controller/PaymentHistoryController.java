package com.jsp.agro1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro1.entity.PaymentHistory;
import com.jsp.agro1.service.PaymentHistoryService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
public class PaymentHistoryController {
	
	@Autowired
	private PaymentHistoryService service;
	
	@PutMapping("/ph")
	public ResponseEntity<ResponseStructure<PaymentHistory>> addPayment(@RequestParam int uid,@RequestParam int pid,@RequestParam String mode){
		return service.addPayment(uid, pid, mode);
	}
}

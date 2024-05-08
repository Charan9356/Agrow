package com.jsp.agro1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.PaymentHistory;
import com.jsp.agro1.repo.PaymentHistoryRepo;

@Repository
public class PaymentHistoryDao {
	@Autowired
	private PaymentHistoryRepo repo;
	
	public PaymentHistory savePaymentHistory(PaymentHistory paymenthistory) {
		return repo.save(paymenthistory);
	}
	public PaymentHistory fetchByID(int id) {
		Optional<PaymentHistory> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
}

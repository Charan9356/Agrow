package com.jsp.agro1.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro1.dao.PaymentHistoryDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.PaymentHistory;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class PaymentHistoryService {
	
	@Autowired
	private PaymentHistoryDao phdao;
	
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<PaymentHistory>> addPayment(int uid,int pid,String mode){
		User udb = udao.fetchByID(uid);
		if(udb!=null) {
			PaymentHistory pdb = phdao.fetchByID(pid);
			if(pdb!=null) {
				pdb.setMode(mode);
				pdb.setPaymentTime(LocalDateTime.now());
				PaymentHistory pay = phdao.savePaymentHistory(pdb);
				ResponseStructure<PaymentHistory> s = new ResponseStructure<PaymentHistory>();
				s.setData(pay);
				s.setMsg("payment added ");
				s.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<PaymentHistory>>(s,HttpStatus.OK);
			}
			else {
				throw new UserNotFound("payment id not found for your search:"+pid);
			}
		}
		else {
			throw new UserNotFound("user not found for your search:"+uid);
		}
	}
}

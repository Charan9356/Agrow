package com.jsp.agro1.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro1.dao.EquipmentsDao;
import com.jsp.agro1.dao.PaymentHistoryDao;
import com.jsp.agro1.dao.RentalDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.Equipments;
import com.jsp.agro1.entity.PaymentHistory;
import com.jsp.agro1.entity.Rental;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.EquipmentNotFound;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class RentalService {
	@Autowired
	private RentalDao rdao;
	
	@Autowired
	private PaymentHistoryDao phdao;
	
	@Autowired
	private EquipmentsDao edao;
	@Autowired
	private UserDao udao;
	
	
	public ResponseEntity<ResponseStructure<Rental>> saveRental(int eid,int uid,String startdate,String enddate){
		Equipments edb = edao.fetchByID(eid);
		if(edb!=null) {
			User udb = udao.fetchByID(uid);
				if(udb!=null) {
					Rental rental = new Rental();
					rental.setEquipments(edb);
					rental.setStartdate(LocalDateTime.parse(startdate));
					rental.setEnddate(LocalDateTime.parse(enddate));
			
					PaymentHistory paymenthistory = new PaymentHistory();
					paymenthistory.setUser(udb);
					double amount = calculateAmount(rental);
					paymenthistory.setAmount(amount);
					PaymentHistory pdb = phdao.savePaymentHistory(paymenthistory);
					rental.setPaymenthistory(pdb);
					rdao.saveRental(rental);
					ResponseStructure<Rental> s = new ResponseStructure<Rental>();
					s.setData(rental);
					s.setMsg("rental added ");
					s.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<Rental>>(s,HttpStatus.OK);
				}
				else {
				throw new UserNotFound("user not found for your search:"+uid);
			}
		}
		else {
			throw new EquipmentNotFound("equipment not found for your search:"+eid);
		}
	}

	private double calculateAmount(Rental rental) {
		Long minutes = Duration.between(rental.getStartdate(), rental.getEnddate()).toMinutes();
		double hours = (double) minutes/60;
		double amount = hours * rental.getEquipments().getCostperhr();
		return amount;
	}
}

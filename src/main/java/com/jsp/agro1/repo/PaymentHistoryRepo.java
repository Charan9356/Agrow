package com.jsp.agro1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.agro1.entity.PaymentHistory;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer>{

}

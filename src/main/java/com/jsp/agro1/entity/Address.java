package com.jsp.agro1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	int id;
	private String houseNumber;
	private String street;
	private String landMark;
	private String mandal;
	private String district;
	private String state;
	private int pinCode;
}

package com.jsp.agro1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Address;
import com.jsp.agro1.repo.AddressRepo;

@Repository
public class AddressDao {
	
	@Autowired
	private AddressRepo repo;
	
	public Address updateAddress(Address address) {
		Optional<Address> db = repo.findById(address.getId());
		if(db.isPresent()) {
			Address a = db.get();
			if(address.getHouseNumber()==null) {
				address.setHouseNumber(a.getHouseNumber());
			}
			if(address.getStreet()==null) {
				address.setState(a.getState());
			}
			if(address.getDistrict()==null) {
				address.setDistrict(a.getDistrict());
			}
			if(address.getLandMark()==null) {
				address.setLandMark(a.getLandMark());
			}
			if(address.getMandal()==null) {
				address.setMandal(a.getMandal());
			}
			if(address.getState()==null) {
				address.setState(a.getState());
			}
			if(address.getPinCode()==0) {
				address.setPinCode(a.getPinCode());
			}
			return repo.save(address);
		}
		else {
			return null;
		}
	}
}

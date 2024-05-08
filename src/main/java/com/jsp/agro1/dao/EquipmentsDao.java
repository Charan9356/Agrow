package com.jsp.agro1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro1.entity.Equipments;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.repo.EquipmentsRepo;

@Repository
public class EquipmentsDao {
	@Autowired
	private EquipmentsRepo repo;

	public Equipments saveEquipment(Equipments equipments) {
		return repo.save(equipments);
	}

	public Equipments fetchByID(int id) {
		Optional<Equipments> db = repo.findById(id);
		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}

	public List<Equipments> fetchAll() {
		return repo.findAll();
	}

	public List<Equipments> fetchEquipmentByName(String Ename) {
		 return repo.fetchEquipmentByName(Ename);
	}

	public List<Equipments> fetchEquipmentByUser(User user) {
		return repo.fetchEquipmentByUser(user);
	}

	public Equipments updateEquipments(Equipments equipments) {
		Optional<Equipments> db = repo.findById(equipments.getId());
		if (db.isPresent()) {
			Equipments e = db.get();
			if (equipments.getEname() == null) {
				equipments.setEname(e.getEname());
			}
			if (equipments.getCostperhr() == 0) {
				equipments.setCostperhr(e.getCostperhr());
			}
			if (equipments.getQuantity() == 0) {
				equipments.setQuantity(e.getQuantity());
			}
			if(equipments.getUser()==null) {
				equipments.setUser(e.getUser());
			}
			return repo.save(equipments);
		} else {
			return null;
		}
	}
	public Equipments deleteEquipmentsByID(int id) {
		Optional<Equipments> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}

}

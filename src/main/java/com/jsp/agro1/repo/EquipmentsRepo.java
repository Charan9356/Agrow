package com.jsp.agro1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.agro1.entity.Equipments;
import com.jsp.agro1.entity.User;

public interface EquipmentsRepo extends JpaRepository<Equipments, Integer>{
	
	@Query("select a from Equipments a where a.Ename=:Ename")
	public abstract List<Equipments> fetchEquipmentByName(String Ename);
	
	@Query("select a from Equipments a where a.user=?1")
	public abstract List<Equipments> fetchEquipmentByUser(User user);
}

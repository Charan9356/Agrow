package com.jsp.agro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro1.dao.EquipmentsDao;
import com.jsp.agro1.dao.UserDao;
import com.jsp.agro1.entity.Equipments;
import com.jsp.agro1.entity.User;
import com.jsp.agro1.exception.EquipmentNotFound;
import com.jsp.agro1.exception.UserNotFound;
import com.jsp.agro1.util.ResponseStructure;

@Service
public class EquipmentsService {
	
	@Autowired
	private EquipmentsDao edao;
	@Autowired
	private UserDao udao;
	
	public ResponseEntity<ResponseStructure<Equipments>> saveEquipments(int uid,String Ename,double costperhr,int quantity){
		User udb = udao.fetchByID(uid);
		if(udb!=null) {
			Equipments equip = new Equipments();
			equip.setEname(Ename);
			equip.setCostperhr(costperhr);
			equip.setQuantity(quantity);
			equip.setUser(udb);
			Equipments edb = edao.saveEquipment(equip);
			ResponseStructure<Equipments> s = new ResponseStructure<Equipments>();
			s.setData(edb);
			s.setMsg("equipment added ");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(s,HttpStatus.OK);
		}
		else {
			throw new UserNotFound("user not found for your search:"+uid);
		}
	}
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(int id){
		Equipments edb = edao.fetchByID(id);
		if(edb!=null) {
			ResponseStructure<Equipments> s = new ResponseStructure<Equipments>();
			s.setData(edb);
			s.setMsg("equipment found successfully");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(s,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNotFound("equipment not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByName(String Ename){
			List<Equipments> edb = edao.fetchEquipmentByName(Ename);
			if(edb.size()>0) {
				ResponseStructure<List<Equipments>> s = new ResponseStructure<List<Equipments>>();
				s.setData(edb);
				s.setMsg("equipment found successfully");
				s.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<List<Equipments>>>(s,HttpStatus.FOUND);
			}
			else {
				throw new EquipmentNotFound("No equipment for your search:"+Ename);
			}
	}
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchAllEquipments(){
		List<Equipments> edb = edao.fetchAll();
		if(edb!=null) {
			ResponseStructure<List<Equipments>> s = new ResponseStructure<List<Equipments>>();
			s.setData(edb);
			s.setMsg("all equipments fetched successfully");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Equipments>>>(s,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNotFound("No equipment for your search:");
		}
	}
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByUser(int user_id){
		 User udb = udao.fetchByID(user_id);
			if(udb!=null) {
				List<Equipments> edb = edao.fetchEquipmentByUser(udb);
				ResponseStructure<List<Equipments>> s = new ResponseStructure<List<Equipments>>();
				s.setData(edb);
				s.setMsg("user equipments fetched successfully");
				s.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<List<Equipments>>>(s,HttpStatus.FOUND);
			}
			else {
				throw new UserNotFound("No user for your search:"+user_id);
			}
		}
	public ResponseEntity<ResponseStructure<Equipments>> updateEquipments(Equipments equipments){
		Equipments edb = edao.fetchByID(equipments.getId());
		if(edb!=null) {
			ResponseStructure<Equipments> s = new ResponseStructure<Equipments>();
			s.setData(edao.updateEquipments(equipments));
			s.setMsg("equipment details updated");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(s,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNotFound("equipment not found for your search:"+equipments.getId());
		}
	}
	public ResponseEntity<ResponseStructure<Equipments>> deleteEquipment(int id){
			Equipments edb = edao.deleteEquipmentsByID(id);
			if(edb!=null) {
			edb.setUser(null);
			edao.deleteEquipmentsByID(id);
			ResponseStructure<Equipments> s = new ResponseStructure<Equipments>();
			s.setData(edb);
			s.setMsg("equipment deleted");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Equipments>>(s,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNotFound("equipment not found for your search:"+id);
		}
	}
}

package com.jsp.agro1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro1.entity.Equipments;
import com.jsp.agro1.service.EquipmentsService;
import com.jsp.agro1.util.ResponseStructure;

@RestController
//@RequestMapping("/eq")
public class EquipmentsController {
	@Autowired
	private EquipmentsService service;
	
	@PostMapping("/eq")
	public ResponseEntity<ResponseStructure<Equipments>> saveEquipments(@RequestParam int uid,@RequestParam String Ename,@RequestParam double costperhr,@RequestParam int quantity){
		return service.saveEquipments(uid, Ename, costperhr, quantity);
	}
	@GetMapping("/eq")
	public ResponseEntity<ResponseStructure<Equipments>> fetchById(@RequestParam int id){
		return service.fetchById(id);
	}
	@GetMapping("/eqn")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByName(@RequestParam String Ename){
		return service.fetchEquipmentByName(Ename);
	}
	@GetMapping("/eqa")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchAllEquipments(){
		return service.fetchAllEquipments();
	}
	@GetMapping("/equ")
	public ResponseEntity<ResponseStructure<List<Equipments>>> fetchEquipmentByUser(@RequestParam int user_id){
		return service.fetchEquipmentByUser(user_id);
	}
	@PutMapping("/eq")
	public ResponseEntity<ResponseStructure<Equipments>> updateUser(@RequestBody Equipments equipments){
		return service.updateEquipments(equipments);
	}
	@DeleteMapping("/eq")
	public ResponseEntity<ResponseStructure<Equipments>> deleteEquipment(@RequestParam int id){
		return service.deleteEquipment(id);
	}
}

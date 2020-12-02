package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.BookedOrders;
import com.active.lk.model.Slot;
import com.active.lk.service.SlotService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value="/slot")

public class SlotController {
	
	@Autowired
	SlotService  slotServ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Slot addNewSlots(@RequestBody Slot slot) {
		return slotServ.createSlot(slot);
	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Slot> getAllSlots() {
		return slotServ.getAllSlots();
		
	}
	
	@RequestMapping(value="/slotId/{slotId}",method= RequestMethod.GET)
	 public Slot getSlotById(@PathVariable String slotId) {
		 return slotServ.getSlot(slotId);
	 
	}
	

	@RequestMapping(value="/acId/{acId}",method= RequestMethod.GET)
	 public Slot getSloByactivityId(@PathVariable String acId) {
		 return slotServ.getSlotByActivityId(acId);
		 
	}
	
	@RequestMapping(value="/slotConfigId/{slotConfigId}",method= RequestMethod.GET)
	 public List<Slot> getSlotBySlotConfigId(@PathVariable String slotConfigId) {
		 return slotServ.getSlotBySlotConfigId(slotConfigId);
		 
	}
	
	@RequestMapping(value="/slotConfigId/{slotConfigId}/slotDate/{slotDate}",method= RequestMethod.GET)
	 public Slot getSlotBySlotConfigIdAndDate(@PathVariable String slotConfigId,@PathVariable String slotDate) {
		 return slotServ.getSlotBySlotConfigIdAndDate(slotConfigId,slotDate);
		 
	}
	
	@RequestMapping(value="/slotDate/{slotDate}/status/{status}",method= RequestMethod.GET)
	 public List<Slot> getSlotDateAndStatus(@PathVariable String slotDate,@PathVariable String status) {
		 return slotServ.getSlotDateAndStatus(slotDate,status);
		 
	}
	
	@RequestMapping(value="/slotId/{slotId}/status/{status}",method= RequestMethod.PUT)
	 public void changeSlotStatus(@PathVariable String slotId,@PathVariable String status) {
		 slotServ.changeSlotStatus(slotId,status);
	 
	}
	
	@RequestMapping(value="/institutionId/{institutionId}",method= RequestMethod.GET)
	 public List<BookedOrders> getAllBookedSlotsByInstitution(@PathVariable String institutionId) {
		 return slotServ.getAllBookedSlotsByInstitution(institutionId);
	 
	}
	
	@RequestMapping(value="/userId/{userId}",method= RequestMethod.GET)
	 public List<BookedOrders> getAllBookedSlotsByUser(@PathVariable String userId) {
		 return slotServ.getAllBookedSlotsByUser(userId);
	 
	}
	
	
	

}

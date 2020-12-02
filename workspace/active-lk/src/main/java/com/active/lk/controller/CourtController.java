package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.Court;

import com.active.lk.service.CourtService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping (value="/court")

public class CourtController {
	
	@Autowired
	CourtService courtServ;
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	public Court addNewCourt(@RequestBody Court court) {
		return courtServ.createCourt(court);
	}
	
	
	@RequestMapping(value="/" , method= RequestMethod.GET)
	public List<Court> getAllCourts(){
		return courtServ.getAllCourts();
	}
	
	@RequestMapping(value="/courtId/{courtId}" , method=RequestMethod.GET) 
	public Court getCourtById(@PathVariable String courtId) {
		return courtServ.getCourt(courtId);
	}
	
	@RequestMapping(value="/activId/{activId}" , method= RequestMethod.GET)
	public List<Court> getActiveCourtByActivId(@PathVariable String activId) {
		return courtServ.getActiveCourtByactivId(activId);
	}
	
	public List<Court> getCourtBySlotId(@PathVariable String slotId) {
		return courtServ.getCourtByslotId(slotId);
	}
	
	@RequestMapping(value="/courtId/{courtId}/status/{status}",method= RequestMethod.PUT)
	 public void changeCourtStatus(@PathVariable String courtId,@PathVariable String status) {
		courtServ.changeCourtStatus(courtId,status);
	 
	}
	
	@RequestMapping(value="/activId/{activId}/all" , method= RequestMethod.GET)
	public List<Court> getCourtByActivId(@PathVariable String activId) {
		return courtServ.getCourtByactivId(activId);
	}
	
	
	
	


}

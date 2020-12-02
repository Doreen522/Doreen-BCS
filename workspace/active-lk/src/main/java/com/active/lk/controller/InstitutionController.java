package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.BookingNumbers;
import com.active.lk.model.Institution;

import com.active.lk.service.InstitutionService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {
	
	@Autowired
	InstitutionService instuServ;
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	public Institution addNewInstitution (@RequestBody Institution institution) {
		return instuServ.createInstitution(institution);
	
	}
	
	@RequestMapping(value="/" , method= RequestMethod.GET)
	public List<Institution> getAllInstitution(){
		return instuServ.getAllInstitution();
	
	}
	
	@RequestMapping(value="/status/{status}" , method= RequestMethod.GET)
	public List<Institution> getAllInstitutionByStatus(@PathVariable String status){
		return instuServ.getAllInstitutionByStatus(status);
	
	}
	
	@RequestMapping(value="/instId/{instId}" , method = RequestMethod.GET)
	public Institution getInstitutionById (@PathVariable String instId) {
		return instuServ.getInstitution(instId);
		
	}
	
	@RequestMapping(value="/userId/{userId}" , method= RequestMethod.GET)
	public Institution getInstitutionByuserid(@PathVariable String userId) {
		return instuServ.getInstitutionByuserId(userId);
	}
	
	@RequestMapping(value="/activity/{activityId}" , method= RequestMethod.GET)
	public List<Institution> getInstitutionByActivity(@PathVariable String activityId) {
		return instuServ.getInstitutionByActivity(activityId);
	}
	
	
	@RequestMapping(value="/bookingnumer/institutionId/{institutionId}" , method= RequestMethod.GET)
	public BookingNumbers getBookingNumbers(@PathVariable String institutionId) {
		return instuServ.getBookingNumbers(institutionId);
	}

	
}

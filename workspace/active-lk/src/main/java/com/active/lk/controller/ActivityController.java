package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.Activity;
import com.active.lk.service.ActivityService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value="/activity")

public class ActivityController {
	
	@Autowired
	ActivityService activeServ;
	
	@RequestMapping(value="/" , method=RequestMethod.POST)
	public Activity addNewAcivity(@RequestBody Activity activity) {
		return activeServ.createActivity(activity);
	}
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public List<Activity> getAllActivity(){
		return activeServ.getAllActivity();
	}
	
	@RequestMapping(value="/active" , method=RequestMethod.GET)
	public List<Activity> getAllActiveActivity(){
		return activeServ.getAllActiveActivity();
	}
		
	@RequestMapping(value="/acId/{acId}" , method=RequestMethod.GET)
	public Activity getActivityById(@PathVariable String acId) {
		return activeServ.getActivity(acId);
	}
	
	@RequestMapping(value="/instId/{instId}" , method=RequestMethod.GET)
	public Activity getActivityByInstitutionId(@PathVariable String instId) {
		return activeServ.getActivityByInstitutionId(instId);
		
	}
	
	
}


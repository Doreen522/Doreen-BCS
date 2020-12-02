package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.Activity;
import com.active.lk.model.Court;
import com.active.lk.model.Institution;
import com.active.lk.model.Slot;
import com.active.lk.model.SlotConfig;
import com.active.lk.model.SlotConfigCreateData;
import com.active.lk.model.User;
import com.active.lk.service.ActivityService;
import com.active.lk.service.CourtService;
import com.active.lk.service.FirebaseService;
import com.active.lk.service.InstitutionService;
import com.active.lk.service.SlotConfigService;
import com.active.lk.service.SlotService;
import com.active.lk.service.UserService;

@RestController
@RequestMapping(value = "/admin")
public class SystemAdminController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	InstitutionService instuServ;
	
	@Autowired
	CourtService courtServ;
	
	@Autowired
	SlotService slotServ;
	
	@Autowired
	ActivityService activeServ;
	
	@Autowired
	FirebaseService firebaseServ;
	
	@Autowired
	SlotConfigService slotConServ;
	
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User createAdminUser(@RequestBody User user) {
		return firebaseServ.createAdminUser(user);
	}
	
	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	  public List<User> getAllUsers() {
	    return userServ.getAllUsers();
	    
	}
	 
	 @RequestMapping(value="/institution", method= RequestMethod.POST)
		public Institution addNewInstitution (@RequestBody Institution institution) {
			return instuServ.createInstitution(institution);
		
	}
		
	@RequestMapping(value="/institution" , method= RequestMethod.GET)
	public List<Institution> getAllInstitution(){
		return instuServ.getAllInstitution();
	
	}
	
	@RequestMapping(value="/institution/status/{status}" , method= RequestMethod.GET)
	public List<Institution> getAllInstitutionByStatus(@PathVariable String status){
		return instuServ.getAllInstitutionByStatus(status);
	
	}
	 
	 @RequestMapping(value="/court", method= RequestMethod.POST)
		public Court addNewCourt(@RequestBody Court court) {
			return courtServ.createCourt(court);
	}
		
	
	@RequestMapping(value="/court" , method= RequestMethod.GET)
	public List<Court> getAllCourts(){
		return courtServ.getAllCourts();
	}
	 
	 
	 @RequestMapping(value = "/slot", method = RequestMethod.POST)
		public Slot addNewSlots(@RequestBody Slot slot) {
			return slotServ.createSlot(slot);
			
	}
	 
	 @RequestMapping(value = "/slot", method = RequestMethod.GET)
		public List<Slot> getAllSlots() {
			return slotServ.getAllSlots();
			
	}
	 
	 @RequestMapping(value="/activity" , method=RequestMethod.POST)
		public Activity addNewAcivity(@RequestBody Activity activity) {
			return activeServ.createActivity(activity);
			
	}
	 
	 @RequestMapping(value="/activity" , method=RequestMethod.GET)
		public List<Activity> getAllActivity(){
			return activeServ.getAllActivity();
			
	}
	 
	 @RequestMapping (value="/slotConfig" , method= RequestMethod.POST)
		public List<SlotConfig> createSlotConfigs(@RequestBody SlotConfigCreateData slotConfigData) {
			return slotConServ.createSlotConfigs(slotConfigData);
					
	}
	 
	 

}

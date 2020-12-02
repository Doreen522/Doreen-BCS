package com.active.lk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.SlotConfig;
import com.active.lk.service.SlotConfigService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value="/slotConfig")

public class SlotConfigController {
	
	
	
	
	@Autowired
	SlotConfigService slotConServ;
	
	
	@RequestMapping (value="/" , method= RequestMethod.POST)
	public SlotConfig addNewConfig(@RequestBody SlotConfig slotConfig) {
		return slotConServ.createSlotConfig(slotConfig);
				
	}
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public List<SlotConfig> getAllSlotConfig(){
	return slotConServ.getAllSlotConfig();
	
	}
	
	@RequestMapping(value="/slotConfigId/{slotConfigId}" , method=RequestMethod.GET)
	public SlotConfig getSlotConfigById(@PathVariable String slotConfigId) {
		return slotConServ.getSlotConfigByScId(slotConfigId);
		
	}
	
	@RequestMapping(value="/courtId/{courtId}", method=RequestMethod.GET)
	public SlotConfig getSlotConfigByCtId(@PathVariable String courtId) {
		return slotConServ.getSlotConfigByCtId(courtId);
	}
	 
	@RequestMapping(value="/activeSlotConfig" ,method= RequestMethod.GET)
	public List <SlotConfig> getAllActiveSlotConfig(){
		return slotConServ.getAllActiveSlotConfig();
	}
	
	@RequestMapping(value="/slotId/{slotId}" ,method= RequestMethod.GET)
	public SlotConfig getBySlot(@PathVariable String slotId){
		return slotConServ.getBySlotId(slotId);
	}
	
	@RequestMapping(value="/instsId/{institutionId}/actvId/{activityId}",method= RequestMethod.GET)
	 public List<SlotConfig> getSlotCongigByInstIdAndActivity(@PathVariable String institutionId,@PathVariable String activityId) {
		 return slotConServ.getByInstIdAndActivity(institutionId,activityId);
		 
	}
	
	@RequestMapping(value="/institutionId/{institutionId}/activityId/{activityId}/slotDate/{slotDate}",method= RequestMethod.GET)
	 public List<SlotConfig> getByInstIdAndAcivityAndSlot(@PathVariable String institutionId,
			 @PathVariable String activityId,@PathVariable String slotDate) {
		 return slotConServ.getByInstIdAndActivityAndSlot(institutionId,activityId, slotDate);
		 
	}
	
	@RequestMapping(value="/instsId/{institutionId}/actvId/{activityId}/courtId/{courtId}/day/{day}",method= RequestMethod.GET)
	 public List<SlotConfig> getSlotCongigByInstIdAndActvAndDay(@PathVariable String institutionId,@PathVariable String activityId,@PathVariable String courtId,@PathVariable String day) {
		 return slotConServ.getByInstIdAndActvAndDay(institutionId,activityId,courtId,day);
		 
	}
	
	@RequestMapping(value="/instsId/{institutionId}",method= RequestMethod.GET)
	 public List<SlotConfig> getSlotCongigByInstIdAndActivity(@PathVariable String institutionId) {
		 return slotConServ.getByInstitution(institutionId);
		 
	}
	
	@RequestMapping(value="/update/price",method = RequestMethod.PUT)
	public SlotConfig updatePriceSlotConfig(@RequestBody SlotConfig slotConfig) {
		return slotConServ.updatePriceSlotConfig(slotConfig);
		
	}
	
	@RequestMapping(value="/delete/slotConfigId/{slotConfigId}",method = RequestMethod.PUT)
	public SlotConfig deleteSlotConfig(@PathVariable String slotConfigId) {
		return slotConServ.deleteSlotConfig(slotConfigId);
		
	}

}

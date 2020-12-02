package com.active.lk.service;

import java.util.List;


import com.active.lk.model.SlotConfig;
import com.active.lk.model.SlotConfigCreateData;

public interface SlotConfigService {
	
	public SlotConfig createSlotConfig(SlotConfig slotconfig);
	
	public List <SlotConfig> getAllSlotConfig();
	
	public SlotConfig getSlotConfigByScId(String SlotConfigId);
	
	public SlotConfig getSlotConfigByCtId(String courtId);

	public List<SlotConfig> getAllActiveSlotConfig();
	
	public SlotConfig getBySlotId(String slotId);
	
	public List<SlotConfig> getByInstIdAndActivity(String institutionId,String activityId);
	
	public List <SlotConfig> getByInstIdAndActivityAndSlot(String institutionId,String activityId,String slotDate);
	
	public List <SlotConfig> getByInstIdAndActvAndDay(String institutionId,String activityId,String courtId,String day);
	
	public List<SlotConfig> getByInstitution(String institutionId);
	
	public List<SlotConfig> getAllByInstitution(String institutionId);
	
	public List<SlotConfig> createSlotConfigs(SlotConfigCreateData slotconfigData);
	
	public SlotConfig updatePriceSlotConfig(SlotConfig slotconfig);
	
	public SlotConfig deleteSlotConfig(String slotConfigId);
	
	public List<SlotConfig> getAllActiveSlotConfigByDay(String day);


	
	
	

}

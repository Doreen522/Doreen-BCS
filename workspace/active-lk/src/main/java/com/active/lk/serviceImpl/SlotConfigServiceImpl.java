package com.active.lk.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.Court;
import com.active.lk.model.Slot;
import com.active.lk.model.SlotConfig;
import com.active.lk.model.SlotConfigCreateData;
import com.active.lk.repo.SlotConfigRepository;
import com.active.lk.service.CourtService;
import com.active.lk.service.SlotConfigService;
import com.active.lk.service.SlotService;

@Service
public class SlotConfigServiceImpl implements SlotConfigService {
	
	@Autowired
	SlotConfigRepository slotConfigRepo;
	
	@Autowired
	SlotService  slotServ;
	
	@Autowired
	CourtService courtServ;

	@Override
	public SlotConfig createSlotConfig(SlotConfig slotConfig) {
		//slotConfig.setStartTime(new DateTime().toDateTimeISO().toDate());
		//slotConfig.setEndTime(new DateTime().toDateTimeISO().toDate());
		return slotConfigRepo.save(slotConfig);
		
	}

	@Override
	public List<SlotConfig> getAllSlotConfig() {
		
		return slotConfigRepo.findAll();
	}

	@Override
	public SlotConfig getSlotConfigByScId(String SlotConfigId) {
		SlotConfig slotConfig=slotConfigRepo.findById(SlotConfigId).get();
		return slotConfig;
	}

	@Override
	public SlotConfig getSlotConfigByCtId(String courtId) {
		SlotConfig slotConfig=slotConfigRepo.findByCtId(courtId);
		return slotConfig;
	}

	@Override
	public List<SlotConfig> getAllActiveSlotConfig() {
		return slotConfigRepo.findAllActiveSlotConfig(StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode());
	}

	@Override
	public SlotConfig getBySlotId(String slotId) {
		Slot slot =slotServ.getSlot(slotId);
		if(slot!=null)
			return slotConfigRepo.findById(slot.getSlotConfigId()).get();
		return null;
	}

	@Override
	public List<SlotConfig> getByInstIdAndActivity(String institutionId, String activityId) {
		return slotConfigRepo.getByInstIdAndActivity(institutionId, activityId, StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString());
		
	}

	
	@Override
	public List<SlotConfig> getByInstIdAndActivityAndSlot(String institutionId, String activityId,String slotDate) {
		List<SlotConfig> slotConfigList =slotConfigRepo.getByInstIdAndActivity(institutionId, activityId, StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString());
		List<SlotConfig> slotCfgWithSlot =  new ArrayList<>();
		for (SlotConfig slotConfig :slotConfigList) {
			Slot slots =slotServ.getSlotBySlotConfigIdAndDate(slotConfig.getId(),slotDate);
			if(slots!=null ) {
				slotCfgWithSlot.add(slotConfig);
			}
		}
		
		return removeSlotConfigWithSameTime(slotCfgWithSlot);
	}
	
	
	private List<SlotConfig> removeSlotConfigWithSameTime(List<SlotConfig> slotConfigAll){
		List<SlotConfig> filteredSlotConfig = new ArrayList<>();
		for (SlotConfig slotConfig :slotConfigAll) {
			if(filteredSlotConfig.size()>0) {
				int count =0;
				for(SlotConfig filSlotConfig :filteredSlotConfig) {
					
					if(filSlotConfig.getStartTime().equalsIgnoreCase(slotConfig.getStartTime()))
						break;
					count++;
				}
				if(filteredSlotConfig.size()==count)
					filteredSlotConfig.add(slotConfig);
			}else
				filteredSlotConfig.add(slotConfig);
		}
		
		return filteredSlotConfig;
		
	}

	@Override
	public List<SlotConfig> getByInstIdAndActvAndDay(String institutionId, String activityId,String courtId, String day) {
		List<SlotConfig> filteredSlotConfig = new ArrayList<>();
		filteredSlotConfig.addAll(slotConfigRepo.getByInstIdAndActvAdnDay(institutionId, activityId,courtId, StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString(),day));
		filteredSlotConfig.addAll(slotConfigRepo.getByInstIdAndActvAdnDay(institutionId, activityId,courtId, StatusEnum.SLOTCONFIG_STATUS_PENDING.getCode().toString(),day));
		return filteredSlotConfig;
	}
	
	@Override
	public List<SlotConfig> getByInstitution(String institutionId) {
		return slotConfigRepo.getByInstitution(institutionId,StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString());
		
	}

	@Override
	public List<SlotConfig> createSlotConfigs(SlotConfigCreateData slotconfigData) {
		List<SlotConfig> createdSlotConfig = new ArrayList<>();
		int numberOfSlotPerDay =slotconfigData.getEndTime()-slotconfigData.getStartTime();
		String[] days = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
		//String [] dayTypes = {DayTypeEnum.DAY_TYPE_NORMAL.getCode().toString(),DayTypeEnum.DAY_TYPE_HOLIDAY.getCode().toString()};
		for(int d=0;d<7;d++) {
		
				for (int i=0 ;i<numberOfSlotPerDay;i++) {
					SlotConfig slotConfig =new SlotConfig();
					Court court =courtServ.getCourt(slotconfigData.getCourtId());
					slotConfig.setActivityId(court.getActivId());;
					slotConfig.setCourtId(slotconfigData.getCourtId());;
					slotConfig.setInstitutionId(court.getInstitutionId());
					//slotConfig.setDayType(dayTypes[dt]);
					slotConfig.setDay(days[d]);
					slotConfig.setHolidayAdultPrice(0);
					slotConfig.setHolidayStudPrice(0);
					slotConfig.setNormalDayAdultPrice(0);
					slotConfig.setNormalDayStudPrice(0);
					slotConfig.setStartTime(String.format("%02d", slotconfigData.getStartTime()+i)+":00");
					slotConfig.setEndTime(String.format("%02d", slotconfigData.getStartTime()+i+1)+":00");
					
					slotConfig.setStatus(StatusEnum.SLOTCONFIG_STATUS_PENDING.getCode().toString());
					
					createdSlotConfig.add(slotConfigRepo.save(slotConfig));
				}
			//}
			
		}
		
		return createdSlotConfig;
	}

	@Override
	public SlotConfig updatePriceSlotConfig(SlotConfig slotconfig) {
		SlotConfig getSlotConfig= getSlotConfigByScId(slotconfig.getId());
		getSlotConfig.setHolidayAdultPrice(slotconfig.getHolidayAdultPrice());
		getSlotConfig.setHolidayStudPrice(slotconfig.getHolidayStudPrice());
		getSlotConfig.setNormalDayAdultPrice(slotconfig.getNormalDayAdultPrice());
		getSlotConfig.setNormalDayStudPrice(slotconfig.getNormalDayStudPrice());
		getSlotConfig.setStatus(StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString());
		return slotConfigRepo.save(getSlotConfig);
	}

	@Override
	public SlotConfig deleteSlotConfig(String slotConfigId) {
		SlotConfig getSlotConfig= getSlotConfigByScId(slotConfigId);
		getSlotConfig.setStatus(StatusEnum.SLOTCONFIG_STATUS_DELETED.getCode().toString());
		return slotConfigRepo.save(getSlotConfig);
	}

	@Override
	public List<SlotConfig> getAllActiveSlotConfigByDay(String day) {
		return slotConfigRepo.findAllActiveSlotConfigByDay(StatusEnum.SLOTCONFIG_STATUS_ACTIVE.getCode().toString(), day);
	}

	@Override
	public List<SlotConfig> getAllByInstitution(String institutionId) {
		 return slotConfigRepo.getAllByInstitution(institutionId);
		
	}


	


}

package com.active.lk.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.BookingNumbers;
import com.active.lk.model.Institution;
import com.active.lk.model.Slot;
import com.active.lk.model.SlotConfig;
import com.active.lk.repo.InstitutionRepository;
import com.active.lk.repo.SlotRepository;
import com.active.lk.service.InstitutionService;
import com.active.lk.service.SlotConfigService;

@Service
public class InstitutionServiceImpl implements InstitutionService{
	
	@Autowired
	InstitutionRepository institutionRepo;
	
	@Autowired
	SlotConfigService slotConServ;
	
	@Autowired 
	SlotRepository slotRepo;

	@Override
	public Institution createInstitution(Institution institution) {
		institution.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
		institution.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return institutionRepo.save(institution);
	}

	@Override
	public List<Institution> getAllInstitution() {
		return institutionRepo.findAll();
	}

	@Override
	public Institution getInstitution(String instId) {
		Institution institution=institutionRepo.findById(instId).get();
		return institution;
	}

	@Override
	public Institution getInstitutionByuserId(String userId) {
		Institution institution=institutionRepo.findByUserid(userId);
		return institution;
	}

	@Override
	public List<Institution> getInstitutionByActivity(String activityId) {
		return institutionRepo.findAllByActivity(activityId,StatusEnum.INSTITUTION_STATUS_ACTIVE.getCode().toString());
	}

	@Override
	public BookingNumbers getBookingNumbers(String institutionId) {
		List<SlotConfig> slotConfigs=slotConServ.getByInstitution(institutionId);
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate = formatter.format(date); 
	    long bookedSlotsForToday =0;
	    long availableSlotsForToday =0;
		for(SlotConfig slotConfig:slotConfigs) {
			if(slotRepo.getSlotBySlotConfigIdAndDate(slotConfig.getId(), StatusEnum.SLOT_STATUS_ACTIVE.getCode().toString(), 
					strDate)!=null) {
				availableSlotsForToday ++;
			}if(slotRepo.getSlotBySlotConfigIdAndDate(slotConfig.getId(), StatusEnum.SlOT_STATUS_BOOKED.getCode().toString(), 
					strDate)!=null) {
				bookedSlotsForToday ++;
			}
			
			
		}
		BookingNumbers bookingNumbers = new BookingNumbers();
		bookingNumbers.setToday(strDate);
		bookingNumbers.setAvailableSlotsForToday(availableSlotsForToday);
		bookingNumbers.setBookedSlotsForToday(bookedSlotsForToday);
		
		return bookingNumbers;
	}

	@Override
	public List<Institution> getAllInstitutionByStatus(String status) {
		
		return institutionRepo.findAllByStatus(status);
	}
	
	
	
}

	

	
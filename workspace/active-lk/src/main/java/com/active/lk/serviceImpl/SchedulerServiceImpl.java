package com.active.lk.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.DayTypeEnum;
import com.active.lk.common.StatusEnum;
import com.active.lk.model.Booking;
import com.active.lk.model.HolidayDates;
import com.active.lk.model.Slot;
import com.active.lk.model.SlotConfig;
import com.active.lk.service.BookingService;
import com.active.lk.service.SchedulerService;
import com.active.lk.service.SlotConfigService;
import com.active.lk.service.SlotService;
import com.active.lk.utils.HolidayDateUtil;

@Service
public class SchedulerServiceImpl implements SchedulerService{
	
	@Autowired
	SlotConfigService slotConServ;
	
	@Autowired
	SlotService slotService;
	
	@Autowired
	BookingService  bookServ;

	@Override
	public void generateSlots(String holidayListFile) {
		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		System.out.println("Current Date : "+dateFormat.format (currentDate));
		// convert date to calendar
		Calendar calender = Calendar.getInstance();
		calender.setTime(currentDate);
		// add 14 days
        calender.add(Calendar.DATE, 14); 
	    // convert calendar to date
        Date dateAfter14 = calender.getTime();
        int dayNum = dateAfter14.getDay();
        String day= getDay(dayNum);
        String dateStr = dateFormat.format(dateAfter14);
        System.out.println("After 14 Days Date : "+dateStr);
        
        HolidayDates holidayDates =HolidayDateUtil.getHolidayList(holidayListFile);
        
       
        List<SlotConfig> slotConfigList = slotConServ.getAllActiveSlotConfigByDay(day);
        for(SlotConfig slotConfig : slotConfigList) {
        	Slot slot = new Slot();
        	slot.setSlotConfigId(slotConfig.getId());
        	slot.setSlotDate(dateStr);
        	if(holidayDates.getHolidayList().size()>0) {
        		if(holidayDates.getHolidayList().contains(dateStr))
        			slot.setDayType(DayTypeEnum.DAY_TYPE_HOLIDAY.getCode().toString());
        		else
        			slot.setDayType(DayTypeEnum.DAY_TYPE_NORMAL.getCode().toString());
        			
        	}
        	slot.setStatus(StatusEnum.SLOT_STATUS_ACTIVE.getCode());
        	slot.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
        	slot.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
        	Slot createdSlot = slotService.createSlot(slot);
        	System.out.println("Created slot : " + createdSlot.getId());
        	
        }
       
	}
	
	private String getDay(int dayNum) {
		String day = null;
		switch(dayNum) {
		  case 0:
			  day="SUN";
		    break;
		  case 1:
			  day="MON";
		    break;
		  case 2:
			  day="TUE";
		    break;
		  case 3:
			  day="WED";
		    break;
		  case 4:
			  day="TUE";
		    break;
		  case 5:
			  day="FRI";
		    break;
		  case 6:
			  day="SAT";
		    break;
		  default:
		    
		}
		return day;
	}

	@Override
	public void updateAcutualizedSlots() {
		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat time =new SimpleDateFormat("HH:mm");
		Date currentDate = new Date();
		String dateStr = dateFormat.format(currentDate);
		
		System.out.println("Current Date : "+dateStr);
		String timeStr = time.format(currentDate);
		System.out.println("Current time : "+timeStr);
		
		List<Slot> bookedSlots = slotService.getSlotDateAndStatus(dateStr, StatusEnum.SlOT_STATUS_BOOKED.getCode().toString());
		for(Slot slot:bookedSlots) {
			SlotConfig slotConfig = slotConServ.getSlotConfigByScId(slot.getSlotConfigId());
			
			if(timeStr.equalsIgnoreCase(slotConfig.getStartTime())) {
				Booking booking = bookServ.getBookingBySlotId(slot.getId());
				if(booking!=null)
					bookServ.actualizedBooking(booking.getBookingRef());
				slotService.changeSlotStatus(slot.getId(),StatusEnum.SLOT_STATUS_ACTUALIZED.getCode().toString());
			}
			
		}
		List<Slot> activeSlots = slotService.getSlotDateAndStatus(dateStr, StatusEnum.SLOT_STATUS_ACTIVE.getCode().toString());
		for(Slot slot:activeSlots) {
			SlotConfig slotConfig = slotConServ.getSlotConfigByScId(slot.getSlotConfigId());
			
			if(timeStr.equalsIgnoreCase(slotConfig.getStartTime())) {
				slotService.changeSlotStatus(slot.getId(),StatusEnum.SlOT_STATUS_DELETED.getCode().toString());
			}
			
		}
		
		
	}

}

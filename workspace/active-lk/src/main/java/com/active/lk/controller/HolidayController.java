package com.active.lk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.HolidayDates;
import com.active.lk.utils.HolidayDateUtil;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value = "/holiday")
public class HolidayController {
	
	@Value("${spring.holidayListFile}")private String holidayListFile;
	
	
	@RequestMapping(value="/date/{date}" , method= RequestMethod.GET)
	public boolean  isHoliday(@PathVariable String date){
		HolidayDates holidayDates =HolidayDateUtil.getHolidayList(holidayListFile);
		if(holidayDates.getHolidayList().size()>0) {
    		if(holidayDates.getHolidayList().contains(date))
    			return true;
    	
    			
		}
		return false;
	}

}

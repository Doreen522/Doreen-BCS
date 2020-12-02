package com.active.lk.utils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.active.lk.model.HolidayDates;

public class HolidayDateUtil {
	
	@SuppressWarnings("unchecked")
	public static HolidayDates getHolidayList(String holidayListFile) {
		HolidayDates holidayDates =new HolidayDates();
		JSONParser parser =new JSONParser();
		try {
			Resource resource = new ClassPathResource("HolidayListFile.txt");

			File file = resource.getFile();
			Object obj= parser.parse(new FileReader(file.getName()));
			JSONObject jsonObject=(JSONObject) obj;
			holidayDates.setCountryCode(jsonObject.get("country_code").toString());
			holidayDates.setHolidayList((List<String>) jsonObject.get("dates"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return holidayDates;
	}

}

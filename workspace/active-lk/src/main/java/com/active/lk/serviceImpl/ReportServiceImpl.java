package com.active.lk.serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.model.Institution;
import com.active.lk.model.ReportData;
import com.active.lk.service.EmailService;
import com.active.lk.service.InstitutionService;
import com.active.lk.service.ReportService;
import com.active.lk.service.SlotService;
import com.opencsv.CSVWriter;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	InstitutionService instServ;
	
	@Autowired
	SlotService slotServ;
	
	@Autowired
	EmailService emailServ;

	@Override
	public void generateReport(String institutionId, String duration,String filePath) {
		Institution inst =instServ.getInstitution(institutionId);
		
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		
		System.out.println("Current Date : "+dateFormat.format (currentDate));
		// convert date to calendar
		Calendar calender = Calendar.getInstance();
		calender.setTime(currentDate);
		//Date startDate = null;
		//Date endDate = null;
		String dateStr = null;
		String reportDate =null;
		String reportDur =null;
		
		if(duration.equalsIgnoreCase("currentMonth")) {	
	        
	        dateStr = dateFormat.format (currentDate);
	        reportDur = "^"+dateStr.substring(0, dateStr.length()-3);
	        System.out.println("Start Date : "+dateStr);
	      
	        calender.set(Calendar.DAY_OF_MONTH,calender.getActualMinimum(Calendar.DAY_OF_MONTH) );
	        String startDate = dateFormat.format(calender.getTime());
	        calender.set(Calendar.DAY_OF_MONTH,calender.getMaximum(Calendar.DAY_OF_MONTH) ); 
	        String endDate = dateFormat.format(calender.getTime());
	        reportDate = startDate +" - "+endDate;
		}
		if(duration.equalsIgnoreCase("lastMonth")) {	
	        
	        calender.add(Calendar.MONTH, -1);
	        dateStr = dateFormat.format(calender.getTime());
	        reportDur = "^"+dateStr.substring(0, dateStr.length()-3);
	        System.out.println("Start Date : "+dateStr);
	        
	        calender.set(Calendar.DAY_OF_MONTH,calender.getActualMinimum(Calendar.DAY_OF_MONTH) );
	        String startDate = dateFormat.format(calender.getTime());
	        calender.set(Calendar.DAY_OF_MONTH,calender.getMaximum(Calendar.DAY_OF_MONTH) ); 
	        String endDate = dateFormat.format(calender.getTime());
	        reportDate = startDate +" - "+endDate;
		}
		if(duration.equalsIgnoreCase("currentYear")) {	
	        
			dateStr = dateFormat.format (currentDate);
	        reportDur = "^"+dateStr.substring(0, dateStr.length()-5);
	        System.out.println("Start Date : "+dateStr);
	        
	        calender.set(Calendar.DAY_OF_YEAR,calender.getActualMinimum(Calendar.DAY_OF_YEAR) );
	        String startDate = dateFormat.format(calender.getTime());
	        calender.set(Calendar.DAY_OF_YEAR,calender.getMaximum(Calendar.DAY_OF_YEAR) ); 
	        String endDate = dateFormat.format(calender.getTime());
	        reportDate = startDate +" - "+endDate;
		}
		List<ReportData>  generatedData =slotServ.getAllSlotsByInstitutionWithDuration(institutionId, reportDur);
		writingToNextColCSV(filePath,generatedData,inst.getName(),reportDate);
		try {
			emailServ.sendMailReport(inst.getName(), reportDate, filePath, inst.getContactEmail());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String writingToNextColCSV(String filePath,List<ReportData> generatedData,String instName,String reportDate) {
		try {
			//File file = new File(filePath);
			FileWriter outputfile = new FileWriter(filePath+"report.csv");
			CSVWriter writer = new CSVWriter(outputfile); 
			String [] header = {instName+" - Report for "+reportDate};
			String [] header1 = {"No","Slot Date","Slot Time","Activity","Court","Price","Status"};
			writer.writeNext(header);
			writer.writeNext(header1);
			long count=0;
			for(ReportData reportData:generatedData) {
				count++;
				String [] data=new String [7];
				data[0]=String.valueOf(count);
				data[1]=reportData.getSlotDate();
				data[2]=reportData.getDuration();
				data[3]=reportData.getActivity();
				data[4]=reportData.getCourt();
				data[5]=String.valueOf(reportData.getPrice());
				data[6]=reportData.getStatus();
				writer.writeNext(data);
				
			}
	        
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "Done";
	}


}

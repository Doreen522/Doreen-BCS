package com.active.lk.serviceImpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.active.lk.model.Activity;
import com.active.lk.model.BookingData;
import com.active.lk.model.Court;
import com.active.lk.model.Institution;
import com.active.lk.model.SlotConfig;
import com.active.lk.model.User;
import com.active.lk.service.ActivityService;
import com.active.lk.service.CourtService;
import com.active.lk.service.EmailService;
import com.active.lk.service.InstitutionService;
import com.active.lk.service.SlotConfigService;
import com.active.lk.service.UserService;

@Service
public class EmailServiceImpl implements EmailService{

	
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	UserService userServ;
	
	@Autowired
	SlotConfigService slotConfigServ;
	
	@Autowired
	ActivityService activityServ;
	
	@Autowired
	InstitutionService instServ;
	
	@Autowired
	CourtService courtServ;
	
	
	public void sendMail(BookingData bookingdata,String status, String mailList) throws MessagingException {
		
		 SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(mailList);
	        
	        User user = userServ.getUser(bookingdata.getUserId());
	        SlotConfig slotconfig =slotConfigServ.getSlotConfigByScId(bookingdata.getSlotConfigId());
	        Activity activity = activityServ.getActivity(slotconfig.getActivityId());
	        Institution inst = instServ.getInstitution(slotconfig.getInstitutionId());
	        Court court = courtServ.getCourt(slotconfig.getCourtId());
	        
	        msg.setSubject("Booking Confirmation - ActiveLK");
	        msg.setText("Hello "+user.getName()+",\n \n Thank You ! Your reservation is now confirmed. \n \n Please Find Your Booking Details"
	        		+ " \n Booking reference No : "+status +
	        		" \n Booking date : "+bookingdata.getSlotDate() + " ( "+slotconfig.getStartTime()+" - "+slotconfig.getEndTime()+" ) "
	        		+ " \n Activity : "+ activity.getName() +
	        		" \n Venue : " + inst.getName()+" ("+inst.getLocation()+") "
	        		+" \n Court : " +court.getName() +" ( "+ court.getVenue() + " ) "+
	        		" \n Price : "+ bookingdata.getPrice() + "LKR "+
	        		" \n \n \n Thank You & Have a Nice Day! \n Terms and Conditions apply");
	        

	        javaMailSender.send(msg);
	}
	
	
	public void sendMailReport(String inst,String reportDate,String fileToAttach, String mailList) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("aru2191@gmail.com");
        helper.setText("<html><body><h1>hello Welcome!</h1><body></html>", true);
        FileSystemResource file  = new FileSystemResource(new File("E:\\files\\"));
        helper.addAttachment("report.csv", file);
       // helper.addAttachment("test.png", new ClassPathResource("test.jpeg"));
        helper.setSubject("Hi");
         
//         MimeMessagePreparator preparator = new MimeMessagePreparator() 
//         {
//             public void prepare(MimeMessage mimeMessage) throws Exception 
//             {
//                 mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("aru2191@gmail.com"));
//                // mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
//                 mimeMessage.setSubject(inst+"Report - ActiveLK");
//                 mimeMessage.setText("Hello Admin,\n \n Please Find Your Report for "+reportDate+"."+
//     	        		
//    	        		" \n \n \n Thank You & Have a Nice Day! \n");
//                  
//                 FileSystemResource file = new FileSystemResource(new File(fileToAttach));
//                 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//                 //helper.addAttachment("report.csv", file);
//             }
//         };
//	        

	        javaMailSender.send(message);
	}

}

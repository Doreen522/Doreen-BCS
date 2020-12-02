package com.active.lk.service;

import javax.mail.MessagingException;

import com.active.lk.model.BookingData;

public interface EmailService {
	
	public void sendMail(BookingData bookingdata, String status, String mailList) throws MessagingException ;
	
	public void sendMailReport(String inst,String reportDate,String fileToAttach, String mailList) throws MessagingException ;

}

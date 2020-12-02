package com.active.lk.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.service.EmailService;

@RestController
@RequestMapping(value = "/email")
public class EmailContoller {
	
	@Autowired
	EmailService emailServ;
	
	@RequestMapping(value="/send/{mailList}")
	public String sendEmail(@PathVariable String mailList) throws AddressException, MessagingException, IOException {
		//emailServ.sendMail("Status:Testing from url", mailList);
		return "Email sent successfully";   
	}

}

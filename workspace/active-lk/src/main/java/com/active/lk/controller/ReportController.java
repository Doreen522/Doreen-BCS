package com.active.lk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.service.ReportService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value = "/report")
public class ReportController {
	
	@Autowired
	ReportService reprtServ;
	
	@Value("${spring.reportFilesPath}") String filePath;
	
	@RequestMapping(value="/institutionId/{institutionId}/duration/{duration}",method= RequestMethod.GET)
	 public void generateReport(@PathVariable String institutionId,@PathVariable String duration) {
		 reprtServ.generateReport(institutionId, duration,filePath);
		 
	}

}

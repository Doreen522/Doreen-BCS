package com.active.lk.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.active.lk.service.SchedulerService;


@Configuration
@EnableScheduling
public class Scheduler {
	
	@Value("${spring.holidayListFile}")private String holidayListFile;
	
	@Autowired
	SchedulerService schedulerServ;
	
  	@Scheduled(cron="0 0 0 * * ? ")
	public void generateSlotsScheduler() {
		schedulerServ.generateSlots(holidayListFile);
		    
	}
  
  
  	@Scheduled(cron="0 */30 * ? * *")
	public void updateAcutualizedSlots() {
		schedulerServ.updateAcutualizedSlots();
		    
	}

}

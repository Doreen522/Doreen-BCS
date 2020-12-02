package com.active.lk.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.Court;
import com.active.lk.repo.CourtRepository;

import com.active.lk.service.CourtService;
import com.active.lk.service.SlotConfigService;

@Service
public class CourtServiceImpl implements CourtService {
	
	@Autowired
	CourtRepository courtRepo;
	
	@Autowired
	SlotConfigService slotConServ;
	

	@Override
	public Court createCourt(Court court) {
		court.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
		court.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return courtRepo.save(court);
	
	}

	@Override
	public List<Court> getAllCourts() {
		return courtRepo.findAll();
	}

	@Override
	public Court getCourt(String courtId) {
		Court court=courtRepo.findById(courtId).get();
				return court;
	}

	@Override
	public List<Court> getCourtByactivId(String activId) {
		List<Court> allCourts = new ArrayList<> ();
		allCourts.addAll(courtRepo.findByactivId(activId,StatusEnum.COURT_STATUS_ACTIVE.getCode().toString()));
		allCourts.addAll(courtRepo.findByactivId(activId,StatusEnum.COURT_STATUS_DELETED.getCode().toString()));
		return allCourts;
			
	}

	@Override
	public List<Court> getCourtByslotId(String slotId) {
		
		return null;
	}

	@Override
	public void changeCourtStatus(String courtId, String status) {
		Court court=courtRepo.findById(courtId).get();
		court.setStatus(status);
		courtRepo.save(court);
	}

	@Override
	public List<Court> getActiveCourtByactivId(String activId) {
		return courtRepo.findByactivId(activId,StatusEnum.COURT_STATUS_ACTIVE.getCode().toString());
	}

}

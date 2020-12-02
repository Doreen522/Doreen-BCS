package com.active.lk.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.BookedOrders;
import com.active.lk.model.Booking;
import com.active.lk.model.ReportData;
import com.active.lk.model.Slot;
import com.active.lk.model.SlotConfig;
import com.active.lk.repo.BookingRepository;
import com.active.lk.repo.SlotRepository;
import com.active.lk.service.ActivityService;
import com.active.lk.service.BookingService;
import com.active.lk.service.CourtService;
import com.active.lk.service.InstitutionService;
import com.active.lk.service.SlotConfigService;
import com.active.lk.service.SlotService;
import com.active.lk.service.UserService;

@Service
public class SlotServiceImpl implements SlotService {
	
	@Autowired 
	SlotRepository slotRepo;
	
	@Autowired
	SlotConfigService slotConServ;

	@Autowired
	BookingService  bookServ;
	
	@Autowired
	BookingRepository  bookRepo;
	
	@Autowired
	UserService userServ;
	
	@Autowired
	CourtService courtServ;
	
	@Autowired
	ActivityService activeServ;
	
	@Autowired
	InstitutionService instuServ;
	
	@Override
	public Slot createSlot(Slot slot) {
		slot.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
		slot.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		return slotRepo.save(slot);
		
		
	}

	@Override
	public List<Slot> getAllSlots() {
		
		return slotRepo.findAll();
	}

	@Override
	public Slot getSlot(String slotId) {
		Slot slot=slotRepo.findById(slotId).get();
		return slot;
	}

	@Override
	public Slot getSlotByActivityId(String acId) {
		Slot slot=slotRepo.findByActivityId(acId);
		return slot;
	}

	@Override
	public List<Slot> getSlotBySlotConfigId(String slotConfigId) {
	
		return slotRepo.findBySlotConfigId(slotConfigId, StatusEnum.SLOT_STATUS_ACTIVE.getCode().toString());
	}

	@Override
	public Slot getSlotBySlotConfigIdAndDate(String slotConfigId, String slotDate) {
		//LocalDateTime date = LocalDateTime.parse(slotDate);
		return slotRepo.getSlotBySlotConfigIdAndDate(slotConfigId,  StatusEnum.SLOT_STATUS_ACTIVE.getCode().toString(), slotDate);
	}

	@Override
	public void changeSlotStatus(String slotId, String status) {
		Slot slot=slotRepo.findById(slotId).get();
		Slot updateSlot =new Slot ();
		updateSlot.setId(slot.getId());
		updateSlot.setStatus(status);
		updateSlot.setSlotConfigId(slot.getSlotConfigId());
		updateSlot.setSlotDate(slot.getSlotDate());
		updateSlot.setCreateDateTime(slot.getCreateDateTime());
		updateSlot.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		slotRepo.save(updateSlot);

		
	}

	@Override
	public List<BookedOrders> getAllBookedSlotsByInstitution(String institutionId) {
		List<SlotConfig> slotConfigs = slotConServ.getByInstitution(institutionId);
		List<BookedOrders> bookedSlots  = new ArrayList<>();
		for(SlotConfig slotConfig :slotConfigs) {
			List<Slot> slots =	slotRepo.findBySlotConfigId(slotConfig.getId(),StatusEnum.SlOT_STATUS_BOOKED.getCode().toString());
			for(Slot slot :slots) {
				Booking booking =bookServ.getBookingBySlotId(slot.getId());
				BookedOrders bookedOrders= new BookedOrders();
				bookedOrders.setSlotDate(slot.getSlotDate());
				bookedOrders.setActivity(activeServ.getActivity(slotConfig.getActivityId()).getName());
				bookedOrders.setBookingId(booking.getBookingRef());
				bookedOrders.setCourt(courtServ.getCourt(slotConfig.getCourtId()).getName());
				bookedOrders.setCustomer(userServ.getUser(booking.getUserId()).getName());
				bookedOrders.setPrice(booking.getPrice());
				bookedOrders.setSlotDuration(slotConfig.getStartTime()+"-"+slotConfig.getEndTime());
				bookedSlots.add(bookedOrders);
			}
		}
		return bookedSlots;
	}
	
	@Override
	public List<BookedOrders> getAllBookedSlotsByUser(String userId) {
		List<Booking> bookings = bookServ.getBookingByUserId(userId);
		List<BookedOrders> bookedSlots  = new ArrayList<>();
		for(Booking booking :bookings) {
			Slot slot =	getSlot(booking.getSlotId());
			SlotConfig slotConfig = slotConServ.getSlotConfigByScId(slot.getSlotConfigId());
			BookedOrders bookedOrders= new BookedOrders();
			bookedOrders.setSlotDate(slot.getSlotDate());
			bookedOrders.setActivity(activeServ.getActivity(slotConfig.getActivityId()).getName());
			bookedOrders.setBookingId(booking.getBookingRef());
			bookedOrders.setCourt(courtServ.getCourt(slotConfig.getCourtId()).getName());
			//bookedOrders.setCustomer(userServ.getUser(booking.getUserId()).getName());
			bookedOrders.setPrice(booking.getPrice());
			bookedOrders.setSlotDuration(slotConfig.getStartTime()+"-"+slotConfig.getEndTime());
			bookedOrders.setInstitution(instuServ.getInstitution(slotConfig.getInstitutionId()).getName());
			bookedSlots.add(bookedOrders);				
			
		}
		return bookedSlots;
	}

	@Override
	public List<Slot> getSlotDateAndStatus(String slotDate, String status) {
		return slotRepo.getSlotByDateAndStatus(slotDate,status);
	}

	@Override
	public List<ReportData> getAllSlotsByInstitutionWithDuration(String institutionId, String date) {
		List<SlotConfig> slotConfigs = slotConServ.getByInstitution(institutionId);
		List<ReportData> reportDataList  = new ArrayList<>();
		for(SlotConfig slotConfig :slotConfigs) {
			List<Slot> slots =	slotRepo.getSlotBySlotConfigIdAndDuration(slotConfig.getId(),date);
			for(Slot slot :slots) {
				Booking booking = new Booking();
				if(slot.getStatus().equalsIgnoreCase(StatusEnum.SLOT_STATUS_ACTUALIZED.getCode().toString()))
					booking =bookRepo.findBySlotId(slot.getId(),StatusEnum.BOOKING_STATUS_ACTUALIZED.getCode().toString());
				else if(slot.getStatus().equalsIgnoreCase(StatusEnum.SlOT_STATUS_BOOKED.getCode().toString()))
					booking=bookServ.getBookingBySlotId(slot.getId());
				ReportData reportData= new ReportData();
				reportData.setSlotDate(slot.getSlotDate());
				reportData.setActivity(activeServ.getActivity(slotConfig.getActivityId()).getName());
				reportData.setCourt(courtServ.getCourt(slotConfig.getCourtId()).getName());
				//bookedOrders.setCustomer(userServ.getUser(booking.getUserId()).getName());
				if(booking!=null)
					reportData.setPrice(booking.getPrice());
				else
					reportData.setPrice(0);
				reportData.setDuration(slotConfig.getStartTime()+"-"+slotConfig.getEndTime());
				reportData.setStatus(slot.getStatus());
				reportDataList.add(reportData);
			}
		}
		return reportDataList;
	}

	
	
	

	
	
	

}

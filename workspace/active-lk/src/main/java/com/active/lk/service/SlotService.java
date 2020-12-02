package com.active.lk.service;

import java.util.Date;
import java.util.List;

import com.active.lk.model.BookedOrders;
import com.active.lk.model.ReportData;
import com.active.lk.model.Slot;

public interface SlotService {
	public Slot createSlot(Slot slot);
	
	public List <Slot> getAllSlots();
	
	public Slot getSlot(String slotId);
	
	public Slot getSlotByActivityId(String acId);
	
	public List<Slot> getSlotBySlotConfigId(String slotConfigId);
	
	public List<Slot> getSlotDateAndStatus(String slotDate,String status);
	
	public Slot getSlotBySlotConfigIdAndDate(String slotConfigId,String slotDate);
	
	public void changeSlotStatus(String slotId,String status);
	
	public List<BookedOrders> getAllBookedSlotsByInstitution(String institutionId);
	
	public List<BookedOrders> getAllBookedSlotsByUser(String userId);
	
	public List<ReportData> getAllSlotsByInstitutionWithDuration(String institutionId,String date);
	
	

}

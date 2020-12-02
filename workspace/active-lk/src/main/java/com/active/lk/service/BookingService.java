package com.active.lk.service;

import java.util.List;

import com.active.lk.model.Booking;
import com.active.lk.model.BookingData;

public interface BookingService {
	public Booking createBooking(BookingData booking);
	
	public List <Booking> getAllBookings();
	
	public List<Booking> getBookingByUserId(String userId);
	
	public Booking getBookingBySlotId(String slotId);

	public Booking getBookingByBkref(String bookingRef);
	
	public Booking cancelByBkref(String bookingRef);
	
	public Booking actualizedBooking(String bookingRef);

	
	
	
}

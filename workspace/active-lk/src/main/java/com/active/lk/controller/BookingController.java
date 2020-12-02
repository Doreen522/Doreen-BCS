package com.active.lk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.active.lk.model.Booking;
import com.active.lk.model.BookingData;
import com.active.lk.service.BookingService;

@CrossOrigin(origins = "*") // For front end communication
@RestController
@RequestMapping(value="/booking")
public class BookingController {
	
	@Autowired
	BookingService  bookServ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Booking addNewBooking(@RequestBody BookingData bookingbata) {
		return bookServ.createBooking(bookingbata);
	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Booking> getAllBookings() {
		return bookServ.getAllBookings();
		
	}
	
	@RequestMapping(value="/userId/{userId}",method= RequestMethod.GET)
	 public List<Booking> getBookingByUserId(@PathVariable String userId) {
		 return bookServ.getBookingByUserId(userId);
	 
	}
	
	@RequestMapping(value="slotId/{slotId}", method= RequestMethod.GET)
	public Booking getBookingBySlotId(@PathVariable String slotId) {
		return bookServ.getBookingBySlotId(slotId);
		
	}
	

	@RequestMapping(value="/bookingRef/{bookingRef}",method= RequestMethod.GET)
	 public Booking getBookingByBkRef(@PathVariable String bookingRef) {
		 return bookServ.getBookingByBkref(bookingRef);
		 
		 
	}
	
	@RequestMapping(value="/bookingRef/{bookingRef}",method = RequestMethod.PUT)
	public Booking cancelBooking(@PathVariable String bookingRef) {
		return bookServ.cancelByBkref(bookingRef);
	}
	
	@RequestMapping(value="/actualized/bookingRef/{bookingRef}",method = RequestMethod.PUT)
	public Booking actualizedBooking(@PathVariable String bookingRef) {
		return bookServ.actualizedBooking(bookingRef);
	}
	
	

}

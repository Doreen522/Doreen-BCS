package com.active.lk.serviceImpl;

import java.util.List;

import javax.mail.MessagingException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.active.lk.common.StatusEnum;
import com.active.lk.model.Booking;
import com.active.lk.model.BookingData;
import com.active.lk.model.Slot;
import com.active.lk.model.User;
import com.active.lk.repo.BookingRepository;

import com.active.lk.service.BookingService;
import com.active.lk.service.EmailService;
import com.active.lk.service.SlotService;
import com.active.lk.service.UserService;
import com.active.lk.utils.GenerateBookingRef;


@Service
public class BookingServiceImpl implements BookingService {
	
	
	@Autowired
	BookingRepository bookRepo;
	
	@Autowired
	SlotService  slotServ;
	
	@Autowired
	EmailService emailServ;
	
	@Autowired
	UserService userServ;

	@Override
	public Booking createBooking(BookingData bookingdata) {
		Booking booking =new Booking();
		booking.setUserId(bookingdata.getUserId());
		booking.setStatus(StatusEnum.BOOKING_STATUS_ACTIVE.getCode().toString());
		booking.setCreateDateTime(new DateTime().toDateTimeISO().toDate());
		booking.setUpdateDateTime(new DateTime().toDateTimeISO().toDate());
		booking.setPrice(bookingdata.getPrice());
		Slot slot = slotServ.getSlotBySlotConfigIdAndDate(bookingdata.getSlotConfigId(), bookingdata.getSlotDate());
		if(slot!=null)
			booking.setSlotId(slot.getId());
		String bookingRef = GenerateBookingRef.randomAlphaNumeric(8);
		booking.setBookingRef(bookingRef);
		Booking newBooking =bookRepo.save(booking);
		if(newBooking!=null && slot!=null) {
			slotServ.changeSlotStatus(slot.getId(), StatusEnum.SlOT_STATUS_BOOKED.getCode().toString());
			User user = userServ.getUser(newBooking.getUserId());
			try {
				emailServ.sendMail(bookingdata, newBooking.getBookingRef(), user.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return booking;
		}
		return null;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookRepo.findAll();
	}

	@Override
	public List<Booking> getBookingByUserId(String userId) {
		return bookRepo.findByUserId(userId,StatusEnum.BOOKING_STATUS_ACTIVE.getCode().toString());
	
	}

	@Override
	public Booking getBookingBySlotId(String slotId) {
		Booking booking= bookRepo.findBySlotId(slotId,StatusEnum.BOOKING_STATUS_ACTIVE.getCode().toString());
		return booking;
	}

	@Override
	public Booking getBookingByBkref(String bookingRef) {
		Booking booking= bookRepo.findByBkref(bookingRef);
		return booking;
	}

	@Override
	public Booking cancelByBkref(String bookingRef) {
		Booking booking =getBookingByBkref(bookingRef);
		booking.setStatus(StatusEnum.BOOKING_STATUS_DELETED.getCode().toString());
		Booking  cancelledBooking = bookRepo.save(booking);
		if(cancelledBooking!=null) {
			slotServ.changeSlotStatus(cancelledBooking.getSlotId(), StatusEnum.SLOT_STATUS_ACTIVE.getCode().toString());
		}
		return cancelledBooking;
	}

	@Override
	public Booking actualizedBooking(String bookingRef) {
		Booking booking =getBookingByBkref(bookingRef);
		booking.setStatus(StatusEnum.BOOKING_STATUS_ACTUALIZED.getCode().toString());
		return  bookRepo.save(booking);

	}
	

}

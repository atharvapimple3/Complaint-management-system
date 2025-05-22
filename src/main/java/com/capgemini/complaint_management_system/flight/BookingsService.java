package com.capgemini.complaint_management_system.flight;

import java.util.List;

public interface BookingsService {

	Bookings saveBooking(Bookings bookings);
	
	Bookings getBookingById(Long bookingId);
	
	List<Bookings> getAllBookings();
	
	Bookings updateBooking(Long bookingId);
	
	boolean deleteBooking(Long bookingId);
	
}

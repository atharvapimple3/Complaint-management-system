package com.capgemini.complaint_management_system.flight;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookingsServiceImpl implements BookingsService {

	BookingsRepository bookingsRepository;

	public BookingsServiceImpl(BookingsRepository bookingsRepository) {
		this.bookingsRepository = bookingsRepository;
	}

	@Override
	public boolean deleteBooking(Long bookingId) {
		Bookings newBooking = bookingsRepository.findById(bookingId).orElseThrow();
		bookingsRepository.delete(newBooking);
		return true;
	}

	@Override
	public List<Bookings> getAllBookings() {
		return bookingsRepository.findAll();
	}

	@Override
	public Bookings saveBooking(Bookings bookings) {
		return bookingsRepository.save(bookings);
	}

	@Override
	public Bookings updateBooking(Long bookingId) {
		Bookings bookings = bookingsRepository.findById(bookingId).orElseThrow();

		Bookings newBooking = new Bookings();
		newBooking.setBookingDate(bookings.getBookingDate());
		newBooking.setAmount(bookings.getAmount());
		newBooking.setBookingTime(bookings.getBookingTime());
		newBooking.setFlightId(bookings.getFlightId());
		newBooking.setSeatClass(bookings.getSeatClass());
		newBooking.setSeatNumber(bookings.getSeatNumber());
		newBooking.setStatus(bookings.getStatus());
		newBooking.setUserId(bookings.getUserId());

		return newBooking;

	}

	@Override
	public Bookings getBookingById(Long bookingId) {
		return bookingsRepository.findById(bookingId).orElseThrow();
	}

}

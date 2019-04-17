package com.hotel.services;

import com.hotel.db.entities.Booking;
import com.hotel.db.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Page<Booking> getAllBookedRooms(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }
}

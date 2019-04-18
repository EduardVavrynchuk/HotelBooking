package com.hotel.services;

import com.hotel.db.entities.Booking;
import com.hotel.db.entities.User;
import com.hotel.db.repositories.BookingRepository;
import com.hotel.db.repositories.UserRepository;
import com.hotel.webapp.transferobject.BookedRoom;
import com.hotel.webapp.transferobject.UserBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public User createUser(UserBody userBody) {
        if (userIsExist(userBody)) {
            logger.info("User with username: " + userBody.getUsername() + " was created!");
            return userRepository.save(new User(userBody.getUsername()));
        }

        logger.info("User with username:" + userBody.getUsername() + " is already exists");
        return null;
    }

    public BookedRoom getFullPriceForBookedRoom(Booking booking) {
        return new BookedRoom(booking);
    }

    public Page<Booking> getUserBooking(User user, Pageable pageable) {
        return bookingRepository.findAllByUser(user, pageable);
    }

    public User getUserById(Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

    public Booking getBookingById(Long booking_id) {
        return bookingRepository.findById(booking_id).orElse(null);
    }

    private boolean userIsExist(UserBody userBody) {
        return userRepository.findByUsername(userBody.getUsername()) == null;
    }

}

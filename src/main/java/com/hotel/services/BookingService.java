package com.hotel.services;

import com.hotel.db.entities.AdditionalOptions;
import com.hotel.db.entities.Booking;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import com.hotel.db.repositories.AdditionalOptionsRepository;
import com.hotel.db.repositories.BookingRepository;
import com.hotel.db.repositories.RoomRepository;
import com.hotel.db.repositories.UserRepository;
import com.hotel.webapp.transferobject.BookingDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.hotel.utils.ConditionValidator.availabilityCheck;
import static com.hotel.utils.ObjectGenerator.getDateFromLong;

@Service
public class BookingService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final AdditionalOptionsRepository additionalOptionsRepository;

    private static final Logger logger = LogManager.getLogger(BookingService.class);

    @Autowired
    public BookingService(
            RoomRepository roomRepository,
            UserRepository userRepository,
            BookingRepository bookingRepository,
            AdditionalOptionsRepository additionalOptionsRepository
    ) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.additionalOptionsRepository = additionalOptionsRepository;
    }

    public Page<Booking> getAllBookedRooms(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    public Booking bookRoom(BookingDTO bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId()).orElse(null);
        Room room = roomRepository.findById(bookingDTO.getRoomId()).orElse(null);

        if (user == null || room == null) {
            logger.warn("User or room was not found");
            return null;
        }

        List<Booking> bookingList = bookingRepository.findAllByRoomAndStartDateGreaterThan(
                room,
                new Date()
        );

        if (bookingList.isEmpty()) {
            return createBooking(user, room, bookingDTO);
        } else {
            Date startDate = getDateFromLong(bookingDTO.getStartDate());
            Date endDate = getDateFromLong(bookingDTO.getEndDate());

            if (availabilityCheck(startDate, endDate, bookingList)) {
                return createBooking(user, room, bookingDTO);
            }

            logger.warn("Room is not available!");
            return null;
        }
    }

    private Booking createBooking(User user, Room room, BookingDTO bookingDTO) {
        Set<AdditionalOptions> additionalOptions = new HashSet<>();

        bookingDTO.getAdditionalOptions().forEach(additionalOptionsDTO -> {
            additionalOptionsRepository.findById(additionalOptionsDTO.getId()).ifPresent(additionalOptions::add);
        });

        Booking booking = new Booking()
                .setUser(user)
                .setRoom(room)
                .setStartDate(getDateFromLong(bookingDTO.getStartDate()))
                .setEndDate(getDateFromLong(bookingDTO.getEndDate()))
                .setAdditionalOptions(additionalOptions);

        logger.info("New booking was created!");
        return bookingRepository.save(booking);
    }
}

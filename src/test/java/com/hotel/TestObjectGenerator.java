package com.hotel;

import com.hotel.db.entities.*;
import com.hotel.db.repositories.*;
import com.hotel.webapp.transferobject.BookingDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Set;

public class TestObjectGenerator {

    public static User generateUser(UserRepository userRepository) {
        return userRepository.save(new User("someUsername" + System.currentTimeMillis()));
    }

    public static CategoryRoom generateCategoryRoom(CategoryRoomRepository categoryRoomRepository) {
        CategoryRoom categoryRoom = new CategoryRoom()
                .setName("someCategory" + System.currentTimeMillis());

        return categoryRoomRepository.save(categoryRoom);
    }

    public static Room generateRoom(CategoryRoom categoryRoom, RoomRepository roomRepository) {
        Room room = new Room()
                .setNumber(new Random().nextInt())
                .setCategory(categoryRoom)
                .setPrice(new Random().nextDouble());

        return roomRepository.save(room);
    }

    public static Booking generateBooking(User user, Room room, Set<AdditionalOptions> additionalOptions, BookingRepository bookingRepository) {
        Booking booking = new Booking()
                .setUser(user)
                .setRoom(room)
                .setStartDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .setEndDate(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .setAdditionalOptions(additionalOptions);

        return bookingRepository.save(booking);
    }

    public static AdditionalOptions generateAdditionalOptions(AdditionalOptionsRepository additionalOptionsRepository) {
        AdditionalOptions additionalOptions = new AdditionalOptions()
                .setName("someName" + System.currentTimeMillis())
                .setPrice(new Random().nextDouble());

        return additionalOptionsRepository.save(additionalOptions);
    }

    public static BookingDTO generateBookingDTO(long userId, long roomId) {

        return new BookingDTO()
                .setUserId(userId)
                .setRoomId(roomId)
                .setStartDate(new Date().getTime())
                .setEndDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)).getTime());
    }
}

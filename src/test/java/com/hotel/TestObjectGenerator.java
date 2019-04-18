package com.hotel;

import com.hotel.db.entities.*;
import com.hotel.db.repositories.AdditionalOptionsRepository;
import com.hotel.db.repositories.BookingRepository;
import com.hotel.db.repositories.CategoryRoomRepository;
import com.hotel.db.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Set;

public class TestObjectGenerator {

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
                .setStartDate(new Date())
                .setEndDate(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .setAdditionalOptions(additionalOptions);

        return bookingRepository.save(booking);
    }

    public static AdditionalOptions generateAdditionalOptions(AdditionalOptionsRepository additionalOptionsRepository) {
        AdditionalOptions additionalOptions = new AdditionalOptions()
                .setName("someName" + System.currentTimeMillis())
                .setPrice(new Random().nextDouble());

        return additionalOptionsRepository.save(additionalOptions);
    }
}

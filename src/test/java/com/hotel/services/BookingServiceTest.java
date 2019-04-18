package com.hotel.services;

import com.hotel.TestConfiguration;
import com.hotel.db.entities.Booking;
import com.hotel.db.entities.CategoryRoom;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import com.hotel.db.repositories.BookingRepository;
import com.hotel.db.repositories.CategoryRoomRepository;
import com.hotel.db.repositories.RoomRepository;
import com.hotel.db.repositories.UserRepository;
import com.hotel.webapp.transferobject.BookingDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static com.hotel.TestObjectGenerator.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRoomRepository categoryRoomRepository;

    @Autowired
    private BookingService bookingService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllBookedRooms() {
        int page = 0, size = 3, amountNewBooking = 2;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        User user = generateUser(userRepository);

        long amountBookedRooms = bookingRepository.count();

        Page<Booking> bookingPage = bookingService.getAllBookedRooms(PageRequest.of(page, size, sort));
        assertEquals(amountBookedRooms, bookingPage.getTotalElements());

        CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);
        Room room = generateRoom(categoryRoom, roomRepository);

        for (int i = 0; i < amountNewBooking; i++) {
            generateBooking(user, room, null, bookingRepository);
        }

        amountBookedRooms+= amountNewBooking;
        assertEquals(amountBookedRooms, bookingRepository.count());

        bookingPage = bookingService.getAllBookedRooms(PageRequest.of(page, size, sort));
        assertEquals(amountBookedRooms, bookingPage.getTotalElements());
    }

    @Test
    public void bookRoom() {
        User user = generateUser(userRepository);

        CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);
        Room room = generateRoom(categoryRoom, roomRepository);

        BookingDTO bookingDTO = generateBookingDTO(user.getId() + new Random().nextLong(), room.getId());

        // expect null, userId is not exist
        Booking booking = bookingService.bookRoom(bookingDTO);
        assertNull(booking);

        long amountBooking = bookingRepository.count();

        bookingDTO.setUserId(user.getId());
        booking = bookingService.bookRoom(bookingDTO);
        assertNotNull(booking);

        assertEquals(amountBooking + 1, bookingRepository.count());

        // expect null, room is not available
        booking = bookingService.bookRoom(bookingDTO);
        assertNull(booking);

    }


}
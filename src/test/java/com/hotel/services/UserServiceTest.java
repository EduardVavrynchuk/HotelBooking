package com.hotel.services;

import com.hotel.TestConfiguration;
import com.hotel.db.entities.*;
import com.hotel.db.repositories.*;
import com.hotel.webapp.transferobject.BookedRoom;
import com.hotel.webapp.transferobject.UserBody;
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

import java.util.Collections;
import java.util.HashSet;

import static com.hotel.TestObjectGenerator.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRoomRepository categoryRoomRepository;

    @Autowired
    private AdditionalOptionsRepository additionalOptionsRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createUser() {
        long userAmount = userRepository.count();

        UserBody userBody = new UserBody()
                .setUsername("someUsername" + System.currentTimeMillis());

        User user = userService.createUser(userBody);
        assertNotNull(user);

        assertEquals(userAmount + 1, userRepository.count());

        // expect null, duplicate username
        user = userService.createUser(userBody);
        assertNull(user);

    }

    @Test
    public void getFullPriceForBookedRoom() {
        UserBody userBody = new UserBody()
                .setUsername("someUsername" + System.currentTimeMillis());

        User user = userService.createUser(userBody);
        assertNotNull(user);

        CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);
        Room room = generateRoom(categoryRoom, roomRepository);
        AdditionalOptions additionalOptions = generateAdditionalOptions(additionalOptionsRepository);
        Booking booking = generateBooking(user, room, new HashSet<>(Collections.singletonList(additionalOptions)), bookingRepository);

        assertNotNull(booking);

        BookedRoom bookedRoom = userService.getFullPriceForBookedRoom(booking);
        double price = bookedRoom.getDaysAmount() * booking.getRoom().getPrice() + additionalOptions.getPrice() * bookedRoom.getDaysAmount();

        assertEquals(String.format("%.2f", price), bookedRoom.getFullPrice());

    }

    @Test
    public void getUserBooking() {
        int totalBookedRoomsForUser = 4;
        long amountBookedRooms = bookingRepository.count();

        UserBody userBody = new UserBody()
                .setUsername("someUsername" + System.currentTimeMillis());

        User user = userService.createUser(userBody);
        assertNotNull(user);

        for (int i = 0; i < totalBookedRoomsForUser; i++) {
            CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);

            Room room = generateRoom(categoryRoom, roomRepository);

            AdditionalOptions additionalOptions = generateAdditionalOptions(additionalOptionsRepository);

            Booking booking = generateBooking(user, room, new HashSet<>(Collections.singletonList(additionalOptions)), bookingRepository);
            assertNotNull(booking);

        }

        assertEquals(amountBookedRooms + totalBookedRoomsForUser, bookingRepository.count());

        int page = 0, size = 3;
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        Page<Booking> bookingPage = userService.getUserBooking(user, PageRequest.of(page, size, sort));
        assertNotNull(bookingPage.getContent());

        assertEquals(totalBookedRoomsForUser, bookingPage.getTotalElements());
    }

    @Test
    public void getUserById() {
        long userAmount = userRepository.count();

        UserBody userBody = new UserBody()
                .setUsername("someUsername" + System.currentTimeMillis());

        User user = userService.createUser(userBody);
        assertNotNull(user);

        assertEquals(userAmount + 1, userRepository.count());

        user = userService.getUserById(user.getId());
        assertNotNull(user);

        // expect null if user id is not exist
        assertNull(userService.getBookingById(user.getId() + 1));
    }

    @Test
    public void getBookingById() {
        long amountBookedRooms = bookingRepository.count();

        UserBody userBody = new UserBody()
                .setUsername("someUsername" + System.currentTimeMillis());

        User user = userService.createUser(userBody);
        assertNotNull(user);

        CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);
        Room room = generateRoom(categoryRoom, roomRepository);
        Booking booking = generateBooking(user, room, null, bookingRepository);

        assertNotNull(booking);

        assertEquals(amountBookedRooms + 1, bookingRepository.count());
        assertNotNull(userService.getBookingById(booking.getId()));
        assertNull(userService.getBookingById(booking.getId() + 1));
    }

}
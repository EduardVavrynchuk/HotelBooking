package com.hotel.services;

import com.hotel.TestConfiguration;
import com.hotel.db.entities.Booking;
import com.hotel.db.entities.CategoryRoom;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import com.hotel.db.repositories.CategoryRoomRepository;
import com.hotel.db.repositories.RoomRepository;
import com.hotel.db.repositories.UserRepository;
import com.hotel.webapp.transferobject.BookingDTO;
import com.hotel.webapp.transferobject.CategoryRoomDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hotel.TestObjectGenerator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRoomRepository categoryRoomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAvailableRooms() {
        Date startDate = new Date();
        Date endDate = java.sql.Date.valueOf(LocalDate.now().plusDays(1));

        User user = generateUser(userRepository);

        CategoryRoom categoryRoom = generateCategoryRoom(categoryRoomRepository);
        Room room = generateRoom(categoryRoom, roomRepository);

        BookingDTO bookingDTO = generateBookingDTO(user.getId(), room.getId());

        List<Room> availableRooms = roomService.getAvailableRooms(startDate, endDate);
        assertNotNull(availableRooms);

        int amountAvailableRooms = availableRooms.size();

        Booking booking = bookingService.bookRoom(bookingDTO);
        assertNotNull(booking);

        availableRooms = roomService.getAvailableRooms(startDate, endDate);
        assertNotNull(availableRooms);

        assertEquals(amountAvailableRooms - 1, availableRooms.size());

    }

    @Test
    public void getRoomsFilteredByCategory() {
        int amountCategories = 4, amountRoomForEachCategory = 2;

        List<CategoryRoom> categoryRoomList = new ArrayList<>();

        for (int i = 0; i < amountCategories; i ++) {
            categoryRoomList.add(generateCategoryRoom(categoryRoomRepository));
        }

        assertEquals(amountCategories, categoryRoomList.size());

        long amountRooms = roomRepository.count();

        categoryRoomList.forEach(categoryRoom -> {
            for(int i = 0; i < amountRoomForEachCategory; i++) {
                generateRoom(categoryRoom, roomRepository);
            }
        });

        assertEquals(amountRooms + amountCategories * amountRoomForEachCategory, roomRepository.count());

        List<CategoryRoomDTO> categoryRoomDTOS = roomService.getRoomsFilteredByCategory();
        assertEquals(categoryRoomRepository.count(), categoryRoomDTOS.size());

        categoryRoomDTOS.forEach(categoryRoomDTO -> {
            if (categoryRoomDTO.getCategoryName().equals(categoryRoomList.get(0).getName())) {
                assertEquals(amountRoomForEachCategory, categoryRoomDTO.getRooms().size());
            }
        });

    }

}
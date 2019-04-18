package com.hotel.services;

import com.hotel.TestConfiguration;
import com.hotel.db.entities.Booking;
import com.hotel.db.entities.CategoryRoom;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import com.hotel.db.repositories.*;
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

import java.util.ArrayList;
import java.util.List;

import static com.hotel.TestObjectGenerator.generateCategoryRoom;
import static com.hotel.TestObjectGenerator.generateRoom;
import static com.hotel.TestObjectGenerator.generateUser;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class RoomServiceTest {

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

    @Autowired
    private RoomService roomService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAvailableRooms() {
        User user = generateUser(userRepository);

        List<Room> generatedRooms = generateRooms();

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

    private List<Room> generateRooms() {
        return null;
    }

    private List<Booking> generateBooking() {
        return null;
        /*
        1	2019-04-03	2019-04-01	2	1
        2	2019-04-05	2019-04-03	3	2
        3	2019-04-07	2019-04-05	4	3
        4	2019-04-23	2019-04-21	5	2
        5	2019-04-25	2019-04-24	6	5

         */
    }
}
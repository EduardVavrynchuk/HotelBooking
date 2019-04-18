package com.hotel.services;

import com.hotel.db.entities.Booking;
import com.hotel.db.entities.CategoryRoom;
import com.hotel.db.entities.Room;
import com.hotel.db.repositories.BookingRepository;
import com.hotel.db.repositories.CategoryRoomRepository;
import com.hotel.db.repositories.RoomRepository;
import com.hotel.utils.ObjectGenerator;
import com.hotel.webapp.transferobject.CategoryRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hotel.utils.ConditionValidator.availabilityCheck;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final CategoryRoomRepository categoryRoomRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public RoomService(
            RoomRepository roomRepository,
            CategoryRoomRepository categoryRoomRepository,
            BookingRepository bookingRepository
    ) {
        this.roomRepository = roomRepository;
        this.categoryRoomRepository = categoryRoomRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Room> getAvailableRooms(Date startDate, Date endDate) {
        List<Room> roomList = roomRepository.findAll();

        List<Room> availableRoom = new ArrayList<>();

        roomList.forEach(room -> {

            List<Booking> bookingList = bookingRepository.findAllByRoomAndStartDateGreaterThan(
                    room,
                    new Date()
            );

            if (bookingList.isEmpty()) {
                availableRoom.add(room);
            } else {
                if (availabilityCheck(startDate, endDate, bookingList)) {
                    availableRoom.add(room);
                }
            }

        });

        return availableRoom;
    }

    public List<CategoryRoomDTO> getRoomsFilteredByCategory() {
        List<CategoryRoom> categoryRooms = categoryRoomRepository.findAll();
        List<Room> rooms = roomRepository.findAll();

        List<CategoryRoomDTO> categoryRoomList = new ArrayList<>();

        categoryRooms.forEach(categoryRoom -> {
            CategoryRoomDTO categoryObject = new CategoryRoomDTO();
            categoryObject.setCategoryName(categoryRoom.getName());
            categoryObject.setRooms(new ArrayList<>());

            rooms.forEach(room -> {
                if (room.getCategory().getId().equals(categoryRoom.getId())) {
                    categoryObject.getRooms().add(ObjectGenerator.generateRoomDTO(room));
                }
            });

            categoryRoomList.add(categoryObject);
        });

        return categoryRoomList;

    }

}

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
import java.util.concurrent.atomic.AtomicBoolean;

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

        Date now = new Date();
        roomList.forEach(room -> {

            List<Booking> bookingList = bookingRepository.findAllByRoomAndStartDateGreaterThan(
                    room,
                    now
            );

            if (bookingList.isEmpty()) {
                availableRoom.add(room);
            } else {
                AtomicBoolean available = new AtomicBoolean(true);

                bookingList.forEach(booking -> {
                    if (startDate.before(booking.getStartDate())) {
                        if (!endDate.before(booking.getStartDate())) {
                            available.set(false);
                        }
                    } else if (!startDate.after(booking.getEndDate())) {
                        available.set(false);
                    }
                });

                if (available.get())
                    availableRoom.add(room);

            }

        });

        return availableRoom;
    }

    public List<CategoryRoomDTO> getRoomsFilteredByCategory() {
        List<CategoryRoom> categoryRooms = categoryRoomRepository.findAll();
        List<Room> rooms = roomRepository.findAll();

        List<CategoryRoomDTO> categoryRoomList = new ArrayList<>();

        categoryRooms.forEach(categoryRoom -> {
            CategoryRoomDTO categoryRoom1 = new CategoryRoomDTO();
            categoryRoom1.setCategoryName(categoryRoom.getName());
            categoryRoom1.setRooms(new ArrayList<>());

            rooms.forEach(room -> {
                if (room.getId().equals(categoryRoom.getId())) {
                    categoryRoom1.getRooms().add(ObjectGenerator.generateRoomDTO(room));
                }
            });

            categoryRoomList.add(categoryRoom1);
        });

        return categoryRoomList;

    }
}

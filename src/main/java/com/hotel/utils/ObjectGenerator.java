package com.hotel.utils;

import com.hotel.db.entities.Room;
import com.hotel.webapp.transferobject.RoomDTO;

import java.util.Date;

public class ObjectGenerator {

    public static RoomDTO generateRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setNumber(room.getNumber());
        roomDTO.setPrice(room.getPrice());

        return roomDTO;
    }

    public static Date getDateFromLong(Long date) {
        if (date == null)
            return null;

        return new Date(date);
    }
}

package com.hotel.webapp.transferobject;

import java.util.List;

public class CategoryRoomDTO {

    private String categoryName;
    private List<RoomDTO> rooms;

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryRoomDTO setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public CategoryRoomDTO setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
        return this;
    }
}

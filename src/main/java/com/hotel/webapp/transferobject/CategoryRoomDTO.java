package com.hotel.webapp.transferobject;

import java.util.List;

public class CategoryRoomDTO {

    private String categoryName;
    private List<RoomDTO> rooms;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }
}

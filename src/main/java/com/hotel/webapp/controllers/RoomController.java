package com.hotel.webapp.controllers;

import com.hotel.services.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hotel/room/")
public class RoomController {

    private final RoomService roomService;

    private static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<?> getAvailableRooms() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "category")
    public ResponseEntity<?> GetAvailableRoomsByCategory() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

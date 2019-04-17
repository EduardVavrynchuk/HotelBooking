package com.hotel.webapp.controllers;

import com.hotel.services.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hotel.utils.ObjectGenerator.getDateFromLong;

@RestController
@RequestMapping(value = "/hotel/room/")
public class RoomController {

    private final RoomService roomService;

    private static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //TODO
    @PostMapping
    public ResponseEntity<?> createRoom() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAvailableRooms(
            @RequestParam("startDate") Long startDate,
            @RequestParam("endDate") Long endDate
    ) {
        return new ResponseEntity<>(roomService.getAvailableRooms(getDateFromLong(startDate), getDateFromLong(endDate)), HttpStatus.OK);
    }

    /**
     * Allow to get List of rooms grouped by categories
     * @return
     *      - Http status 200 with list objects, if success
     */
    @GetMapping(value = "category")
    public ResponseEntity<?> GetRoomsByCategory() {
        return new ResponseEntity<>(roomService.getRoomsFilteredByCategory(), HttpStatus.OK);
    }

}

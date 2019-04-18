package com.hotel.webapp.controllers;

import com.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.hotel.utils.ObjectGenerator.getDateFromLong;

@RestController
@RequestMapping(value = "/hotel/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Allow to retrieve info about available rooms for specified dates
     * @param startDate - start date booking
     * @param endDate - end date booking
     * @return
     *      - Http status 200 with list of available rooms
     */
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
    @GetMapping(value = "/category")
    public ResponseEntity<?> getRoomsByCategory() {
        return new ResponseEntity<>(roomService.getRoomsFilteredByCategory(), HttpStatus.OK);
    }

}

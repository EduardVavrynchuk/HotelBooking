package com.hotel.webapp.controllers;

import com.hotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hotel/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Allow to get all history of booked number in page format
     * @param size - amount booked rooms on page
     * @param page - number page
     * @return
     *      - Http status 200 with Page object, if success
     */
    @GetMapping
    public ResponseEntity<?> getAllBooking(@RequestParam("size") int size, @RequestParam("page") int page) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        return new ResponseEntity<>(bookingService.getAllBookedRooms(PageRequest.of(page, size, sort)), HttpStatus.OK);
    }
}

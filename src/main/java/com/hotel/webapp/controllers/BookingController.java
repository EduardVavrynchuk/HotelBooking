package com.hotel.webapp.controllers;

import com.hotel.db.entities.Booking;
import com.hotel.services.BookingService;
import com.hotel.webapp.transferobject.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "hotel/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Allows to get all history of booked number in page format
     * @param size - amount booked rooms on page
     * @param page - number page
     * @return
     *      - Http status 200 with Page object, if success
     */
    @GetMapping
    public ResponseEntity<?> getAllBooking(
            @RequestParam("size") int size,
            @RequestParam("page") int page
    ) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        return new ResponseEntity<>(bookingService.getAllBookedRooms(PageRequest.of(page, size, sort)), HttpStatus.OK);
    }

    /**
     * Allows to create a new booking room for specified user
     * @param bookingDTO - object with input parameters
     * @param errors - field validation
     * @return
     *      - Http status 400, if User or Room was not found, or room is not available
     *      - Http status 200 with created booking, if success
     */
    @PostMapping
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody BookingDTO bookingDTO, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>("Required fields is empty", HttpStatus.BAD_REQUEST);
        }

        Booking booking = bookingService.bookRoom(bookingDTO);

        if (booking == null)
            return new ResponseEntity<>("Incorrect entered data", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}

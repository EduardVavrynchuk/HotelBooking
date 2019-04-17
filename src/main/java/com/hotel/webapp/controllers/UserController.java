package com.hotel.webapp.controllers;

import com.hotel.db.entities.Booking;
import com.hotel.db.entities.User;
import com.hotel.services.UserService;
import com.hotel.webapp.transferobject.UserBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/hotel/user")
public class UserController {

    private final UserService userService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Allows to create a new user
     * @param userBody - object with one parameter - username
     * @param errors - validation on NULL and empty for field - username
     * @return
     *      - Http status 200 with User entity, if user was created success
     *      - Http status 400, if accepted param is not valid (username field is null or empty)
     *      - Htt status 409, if user is already exist
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserBody userBody, Errors errors) {
        if (errors.hasFieldErrors("username")) {
            logger.info("Username is null");
            return new ResponseEntity<>("Username field not be null or empty", HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(userBody);

        if (user == null) {
            logger.info("User with username: " + userBody.getUsername() + " is already exist");
            return new ResponseEntity<>("Username is already exist", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Allow to get all user booked rooms
     * @param user_id - id of user for which need to get booked rooms
     * @return
     *      - Http status 400, if user was not found by user_id
     *      - Http status 200 with list of all booked rooms, if success
     */
    @GetMapping(value = "/{user_id}")
    public ResponseEntity<?> getUserBookings(@PathVariable("user_id") Long user_id) {
        User user = userService.getUserById(user_id);

        if (user == null) {
            logger.info("User with id: " + user_id + " not found");
            return new ResponseEntity<>("User with id: " + user_id + " not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.getUserBooking(user), HttpStatus.OK);
    }

    /**
     * Allow to get full price for booked room
     * @param booking_id - id booking
     * @return
     *      - Http status 400, if booked number was not found
     *      - Http status 200 with object which contain amount days and price, if success
     */
    @GetMapping(value = "/booked/{booking_id}")
    public ResponseEntity<?> getPriceForBookedRoom(
            @PathVariable("booking_id") Long booking_id
    ) {

        Booking booking = userService.getBookingById(booking_id);

        if (booking == null) {
            logger.info("Booking with id: " + booking_id + " not found");
            return new ResponseEntity<>("Booking with id: " + booking_id + " not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.getFullPriceForBookedRoom(booking), HttpStatus.OK);
    }

}

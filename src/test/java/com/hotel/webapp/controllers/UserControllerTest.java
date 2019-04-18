package com.hotel.webapp.controllers;

import com.hotel.TestConfiguration;
import com.hotel.webapp.transferobject.UserBodyDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Errors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource("classpath:test.properties")
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createUser() {
        UserBodyDTO userBodyDTO = new UserBodyDTO()
                .setUsername("someUsername" + System.currentTimeMillis());

        Errors errors = mock(Errors.class);

        when(errors.hasFieldErrors("username")).thenReturn(true);

        ResponseEntity responseEntity = userController.createUser(userBodyDTO, errors);
        assertEquals("Username field not be null or empty", responseEntity.getBody());

        when(errors.hasFieldErrors("username")).thenReturn(false);

        responseEntity = userController.createUser(userBodyDTO, errors);
        assertNotNull(responseEntity.getBody());

        responseEntity = userController.createUser(userBodyDTO, errors);
        assertEquals("User is already exist", responseEntity.getBody());
    }

    @Ignore
    @Test
    public void getPriceForUserBookedRoom() {
    }

    @Ignore
    @Test
    public void getUserBookedRooms() {
    }
}
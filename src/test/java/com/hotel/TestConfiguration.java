package com.hotel;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.test.context.TestConfiguration
@ComponentScan(
        basePackages = {
                "com.hotel.webapp.controllers",
                "com.hotel.services",
                "com.hotel.db"
        }
)
@EnableAutoConfiguration
public class TestConfiguration {

}

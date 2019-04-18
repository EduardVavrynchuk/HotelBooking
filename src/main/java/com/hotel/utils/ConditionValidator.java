package com.hotel.utils;

import com.hotel.db.entities.Booking;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConditionValidator {

    public static boolean availabilityCheck(Date startDate, Date endDate, List<Booking> bookingList) {
        AtomicBoolean available = new AtomicBoolean(true);

        bookingList.forEach(booking -> {
            if (startDate.before(booking.getStartDate()) && !startDate.equals(booking.getStartDate())) {
                if (!endDate.before(booking.getStartDate()) && !endDate.equals(booking.getStartDate())) {
                    available.set(false);
                }
            } else if (!startDate.after(booking.getEndDate()) && !startDate.equals(booking.getEndDate())) {
                available.set(false);
            }
        });

        return available.get();
    }
}

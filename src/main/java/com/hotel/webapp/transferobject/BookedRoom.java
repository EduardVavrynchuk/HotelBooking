package com.hotel.webapp.transferobject;

import com.hotel.db.entities.Booking;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class BookedRoom extends Booking {

    private int daysAmount;
    private Double fullPrice;


    public BookedRoom(Booking booking) {
        this.setId(booking.getId());
        this.setRoom(booking.getRoom());
        this.setAdditionalOptions(booking.getAdditionalOptions());
        this.setStartDate(booking.getStartDate());
        this.setEndDate(booking.getEndDate());
        this.daysAmount = Days.daysBetween(new LocalDate(this.getStartDate().getTime()), new LocalDate(this.getEndDate().getTime())).getDays();

        calculateFullPrice();
    }

    public int getDaysAmount() {
        return daysAmount;
    }

    public void setDaysAmount(int daysAmount) {
        this.daysAmount = daysAmount;
    }

    public String getFullPrice() {
        return String.format("%.2f", fullPrice);
    }

    public void calculateFullPrice() {
        double priceForAdditionalOptions = this.getAdditionalOptions().stream().mapToDouble(additionalOptions -> daysAmount * additionalOptions.getPrice()).sum();

        this.fullPrice = daysAmount * this.getRoom().getPrice() + priceForAdditionalOptions;
    }
}

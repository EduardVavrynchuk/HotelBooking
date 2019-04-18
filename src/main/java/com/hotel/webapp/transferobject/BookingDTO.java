package com.hotel.webapp.transferobject;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BookingDTO {

    @NotNull
    private long userId;

    @NotNull
    private long roomId;

    @NotNull
    private long startDate;

    @NotNull
    private long endDate;

    private List<AdditionalOptionsDTO> additionalOptions;

    public BookingDTO() {
    }

    public long getUserId() {
        return userId;
    }

    public BookingDTO setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getRoomId() {
        return roomId;
    }

    public BookingDTO setRoomId(long roomId) {
        this.roomId = roomId;
        return this;
    }

    public long getStartDate() {
        return startDate;
    }

    public BookingDTO setStartDate(long startDate) {
        this.startDate = startDate;
        return this;
    }

    public long getEndDate() {
        return endDate;
    }

    public BookingDTO setEndDate(long endDate) {
        this.endDate = endDate;
        return this;
    }

    public List<AdditionalOptionsDTO> getAdditionalOptions() {
        return additionalOptions;
    }

    public BookingDTO setAdditionalOptions(List<AdditionalOptionsDTO> additionalOptions) {
        this.additionalOptions = additionalOptions;
        return this;
    }
}

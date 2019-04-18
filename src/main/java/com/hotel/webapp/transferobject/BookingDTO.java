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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public List<AdditionalOptionsDTO> getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(List<AdditionalOptionsDTO> additionalOptions) {
        this.additionalOptions = additionalOptions;
    }
}

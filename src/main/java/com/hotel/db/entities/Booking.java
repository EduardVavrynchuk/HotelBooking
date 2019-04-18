package com.hotel.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="booking_seq")
    @SequenceGenerator(
            name="booking_seq",
            sequenceName="booking_sequence",
            allocationSize=1
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private Room room;

    @ManyToMany
    private Set<AdditionalOptions> additionalOptions;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Booking setUser(User user) {
        this.user = user;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Booking setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Set<AdditionalOptions> getAdditionalOptions() {
        return additionalOptions;
    }

    public Booking setAdditionalOptions(Set<AdditionalOptions> additionalOptions) {
        this.additionalOptions = additionalOptions;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Booking setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Booking setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
}

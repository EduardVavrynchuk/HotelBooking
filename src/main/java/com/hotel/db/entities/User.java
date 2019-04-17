package com.hotel.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "hotel_user")
public class User {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hotel_seq")
    @SequenceGenerator(
            name="hotel_seq",
            sequenceName="hotel_sequence",
            allocationSize=1
    )
    private Long id;

    @Column(name = "username")
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

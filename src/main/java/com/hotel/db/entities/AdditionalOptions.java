package com.hotel.db.entities;

import javax.persistence.*;

@Entity
public class AdditionalOptions {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public AdditionalOptions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AdditionalOptions setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public AdditionalOptions setPrice(Double price) {
        this.price = price;
        return this;
    }

}

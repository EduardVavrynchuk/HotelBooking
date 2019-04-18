package com.hotel.db.entities;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private int number;

    @OneToOne
    private CategoryRoom category;

    @Column(name = "price")
    private Double price;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public Room setNumber(int number) {
        this.number = number;
        return this;
    }

    public CategoryRoom getCategory() {
        return category;
    }

    public Room setCategory(CategoryRoom category) {
        this.category = category;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Room setPrice(Double price) {
        this.price = price;
        return this;
    }
}

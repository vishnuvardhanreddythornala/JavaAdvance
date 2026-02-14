package com.cap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String category;

    @Column
    private boolean available;
    
    public Menu() {
    }
    public Menu(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    @Override
    public String toString() {
        return id + " | " + name + " | ₹" + price + " | " + category + " | " + available;
    }
}

package com.cap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", initialValue = 1, allocationSize = 1)
    private int id;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "customer_id")   
    private Customer customer;

    public Order() {}

    public Order(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {  
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

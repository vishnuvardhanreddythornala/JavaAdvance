package com.cap.BookStroreRest.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {

        this.title = title;
        this.author = author;
        this.price = price;
    }

}

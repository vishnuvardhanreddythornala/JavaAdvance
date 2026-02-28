package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Exception.BookNotFoundException;
import com.example.LibraryManagementSystem.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BookService {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }
    public List<Book> getAllBooks() {
        return books;
    }
    public Book getBookById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID "+id+" not found"));
    }
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        books.remove(book);
    }

}

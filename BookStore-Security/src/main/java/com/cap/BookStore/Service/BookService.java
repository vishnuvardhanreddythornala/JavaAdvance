package com.cap.BookStore.Service;

import com.cap.BookStore.dto.BookRequest;
import com.cap.BookStore.dto.BookResponse;
import com.cap.BookStore.entity.Book;
import com.cap.BookStore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public BookResponse addBook(BookRequest request){
        Book book = modelMapper.map(request, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookResponse.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }
}

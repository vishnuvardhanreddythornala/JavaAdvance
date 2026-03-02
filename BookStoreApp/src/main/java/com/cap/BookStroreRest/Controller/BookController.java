package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.BookDto;
import com.cap.BookStroreRest.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto dto){
        BookDto saveBook = bookService.createBook(dto);
        return ResponseEntity.ok(savedBook);
    }
}

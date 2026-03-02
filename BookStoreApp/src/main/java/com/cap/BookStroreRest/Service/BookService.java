package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.BookDto;
import com.cap.BookStroreRest.Entity.Book;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final ModelMapper modelMapper;

     BookService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDto createBook(@Valid BookDto dto) {

        Book book = modelMapper.map(dto,Book.class);
        Book saved = respository.save(book);

        return modelMapper.map(saved,BookDto.class);

    }
}

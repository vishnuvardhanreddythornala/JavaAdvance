package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.BookDto;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.Entity.Book;
import com.cap.BookStroreRest.Repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    public BookDto createBook(@Valid BookDto bookDto) {

        Book book = modelMapper.map(bookDto,Book.class);//Dto to entity conversion

        Book saveBook = bookRepository.save(book);//save the book data

        return modelMapper.map(saveBook,BookDto.class);//entity to Dto
    }
    public BookDto updateBookbyId(Long id, BookDto bookDto){
        Book existingbook =  bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book ID is not found"));

        existingbook.setTitle(bookDto.getTitle());
        existingbook.setAuthor(bookDto.getAuthor());
        existingbook.setPrice(bookDto.getPrice());

        Book updatedBook =  bookRepository.save(existingbook);
        return modelMapper.map(updatedBook, BookDto.class);
    }
    //update
    public BookDto getBookById(Long id){
        Book book  =  bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found"));
        return modelMapper.map(book, BookDto.class);
    }
    //delete
    public BookDto deleteById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);

        return modelMapper.map(book, BookDto.class);
    }
    public List<BookDto> getAllBooks(){
        List<Book> bookList =  bookRepository.findAll();
        return bookList.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }
    public PageResponse<BookDto>  getBooks(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        List<BookDto> dtoList =  bookPage.getContent()
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
        return new PageResponse<>(
                dtoList,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements(),
                bookPage.getTotalPages()

        );
    }
}

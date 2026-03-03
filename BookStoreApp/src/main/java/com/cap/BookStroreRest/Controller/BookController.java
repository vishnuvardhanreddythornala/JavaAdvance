package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.BookDto;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "Book Management APIs")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    @Operation(
            summary = "Add a new book",
            description = "Creates a new book in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<BookDto> createBook(
            @RequestBody @Valid BookDto bookDto) {

        BookDto createdBook = bookService.createBook(bookDto);
        return ResponseEntity.status(201).body(createdBook);
    }

    @GetMapping("/getAllBooks")
    @Operation(
            summary = "Get all books",
            description = "Retrieves all books from the database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books fetched successfully")
    })
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getBook/{id}")
    @Operation(
            summary = "Get book by ID",
            description = "Retrieves a single book using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> getBookById(
            @Parameter(description = "ID of the book")
            @PathVariable Long id) {

        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/updateBook/{id}")
    @Operation(
            summary = "Update book",
            description = "Updates book details using ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> updateBook(
            @Parameter(description = "ID of the book to update")
            @PathVariable Long id,
            @RequestBody @Valid BookDto bookDto) {

        return ResponseEntity.ok(
                bookService.updateBookbyId(id, bookDto)
        );
    }

    @DeleteMapping("/deleteBook/{id}")
    @Operation(
            summary = "Delete book",
            description = "Deletes a book using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookDto> deleteBook(
            @Parameter(description = "ID of the book to delete")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookService.deleteById(id)
        );
    }

    @GetMapping("/page")
    @Operation(
            summary = "Get books with pagination and sorting",
            description = "Fetch books using page number, size, sorting field and direction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books fetched successfully")
    })
    public ResponseEntity<PageResponse<BookDto>> getBooks(

            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of records per page")
            @RequestParam(defaultValue = "5") int size,

            @Parameter(description = "Field to sort by (id, title, author, price)")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sorting direction: asc or desc")
            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                bookService.getBooks(page, size, sortBy, direction)
        );
    }
}
package com.example.BookAplication.Controller;

import com.example.BookAplication.Dto.BookRequestDTO;
import com.example.BookAplication.Dto.BookResponseDTO;
import com.example.BookAplication.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookRequestDTO book) {
        BookResponseDTO savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("bookId") Integer bookId) {
        BookResponseDTO getBookById = bookService.getBookById(bookId);
        return ResponseEntity.ok(getBookById);
    }

    @GetMapping("/search/{bookName}")
    public ResponseEntity<BookResponseDTO> getBookByName(@PathVariable("bookName") String name) {
        BookResponseDTO bookByName = bookService.getBookByName(name);
        return ResponseEntity.ok(bookByName);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody BookRequestDTO dto) {
        BookResponseDTO updatedBook = bookService.updateBook(bookId,dto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

}
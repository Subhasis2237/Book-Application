package com.example.BookAplication.Service;

import com.example.BookAplication.Dto.BookRequestDTO;
import com.example.BookAplication.Dto.BookResponseDTO;
import com.example.BookAplication.Entity.Book;
import com.example.BookAplication.Exception.BookNotFoundException;
import com.example.BookAplication.Repository.BookRepository;
import com.example.BookAplication.Mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDTO addBook(BookRequestDTO request) {
        log.info("Adding new book with title: {}", request.getTitle());
        Book book = BookMapper.toEntity(request);
        Book save = bookRepository.save(book);
        log.info("Book saved successfully with the id: {}", save.getId());
        return BookMapper.toDTO(save);
    }

    public BookResponseDTO getBookByName(String name) {
        log.info("Searching book with title: {}", name);
        Book bookByTitle = Optional.ofNullable(bookRepository.findBookByTitle(name))
                .orElseThrow(() -> {
                    log.error("Book not found with title: {}", name);
                    return new BookNotFoundException("Book Not Found with title: " + name);
                });
        return BookMapper.toDTO(bookByTitle);
    }

    public BookResponseDTO updateBook(Integer id, BookRequestDTO book) {
        log.info("Updating book with id: {}",id);
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update. Book not found with id: {}",id);
                    return new BookNotFoundException("Book Not Found with id: "+id);
                });
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());
        log.info("Book updated successfully with id: {}",id);
        return BookMapper.toDTO(bookRepository.save(existingBook));
    }

    public void deleteBook(Integer bookId) {
        log.info("Deleting book with id: {}", bookId);
        if(!bookRepository.existsById(bookId)) {
            log.error("Cannot delete. Book not found with id: {}", bookId);
            throw new BookNotFoundException("Book Not Found with id: " + bookId);
        }
        bookRepository.deleteById(bookId);
        log.info("Book deleted successfully with id: {}", bookId);
    }

    public BookResponseDTO getBookById(Integer bookId) {
        log.info("fetching book with id: {}", bookId);
        Book bookById = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    log.error("Book not found with id: {}", bookId);
                    return new BookNotFoundException("Book not found with id: " + bookId);
                });
        return BookMapper.toDTO(bookById);
    }
}

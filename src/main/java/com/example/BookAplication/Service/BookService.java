package com.example.BookAplication.Service;

import com.example.BookAplication.Dto.BookRequestDTO;
import com.example.BookAplication.Dto.BookResponseDTO;
import com.example.BookAplication.Entity.Book;
import com.example.BookAplication.Exception.BookNotFoundException;
import com.example.BookAplication.Repository.BookRepository;
import com.example.BookAplication.Mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDTO addBook(BookRequestDTO request) {
        Book book = BookMapper.toEntity(request);
        Book save = bookRepository.save(book);
        return BookMapper.toDTO(save);
    }

    public BookResponseDTO getBookByName(String name) {
        Book bookByTitle = Optional.ofNullable(bookRepository.findBookByTitle(name))
                .orElseThrow(() -> new BookNotFoundException("Book Not Found with title: "+name));
        return BookMapper.toDTO(bookByTitle);
    }

    public BookResponseDTO updateBook(Integer id, BookRequestDTO book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());

        return BookMapper.toDTO(bookRepository.save(existingBook));
    }

    public void deleteBook(Integer bookId) {
        if(!bookRepository.existsById(bookId))
            throw new BookNotFoundException("Book Not Found with id: "+bookId);
        bookRepository.deleteById(bookId);
    }

    public BookResponseDTO getBookById(Integer bookId) {
        Book bookById = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: "+bookId));

        return BookMapper.toDTO(bookById);
    }
}

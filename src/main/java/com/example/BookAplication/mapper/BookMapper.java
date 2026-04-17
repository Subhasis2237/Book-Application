package com.example.BookAplication.mapper;

import com.example.BookAplication.Dto.BookRequestDTO;
import com.example.BookAplication.Dto.BookResponseDTO;
import com.example.BookAplication.Entity.Book;

public class BookMapper {

    public static Book toEntity(BookRequestDTO dto) {
        return new Book(
                null,
                dto.getBookName(),
                dto.getAuthor(),
                dto.getGenre()
        );
    }

    public static BookResponseDTO toDTO(Book book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .bookName(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .build();
    }

}

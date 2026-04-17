package com.example.BookAplication.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDTO {
    private Integer id;
    private String bookName;
    private String author;
    private String genre;
}

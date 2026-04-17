package com.example.BookAplication.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDTO {

    @NotBlank(message = "Name is required")
    private String bookName;

    @NotBlank(message = "Author is required")
    private String author;

    private String genre;
}

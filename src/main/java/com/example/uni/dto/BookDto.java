package com.example.uni.dto;

import com.example.uni.enums.BookStatus;
import com.example.uni.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String name;
    private String publisher;
    private String author;
    private String image;
    private String publicationDate;
    private Long price;
    private String content;
    private BookType type;
    private String userId;
    private BookStatus status; // default
}

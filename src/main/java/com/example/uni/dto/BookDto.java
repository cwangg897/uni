package com.example.uni.dto;

import com.example.uni.enums.BookStatus;
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
    private String title;
    private String publisher;
    private String author;
    private String image;
    private String publicationDate;
    private Long price;
    private String content;
    private BookStatus status; // 대출인지 구매인지 거래완료인지
    private String userId;

}

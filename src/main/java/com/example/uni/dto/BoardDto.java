package com.example.uni.dto;


import java.time.LocalDateTime;

import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String userId;
}

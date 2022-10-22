package com.example.uni.dto;

import java.time.LocalDateTime;

import lombok.*;


@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {

    private Long id;
    private Long boardId;
    private String userId;
    private String content;
}

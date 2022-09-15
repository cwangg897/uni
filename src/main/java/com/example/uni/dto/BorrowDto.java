package com.example.uni.dto;


import java.time.LocalDateTime;

import com.example.uni.enums.BorrowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BorrowType type;
    private String userId;
    private Long bookId;
    private String visitTime;
}

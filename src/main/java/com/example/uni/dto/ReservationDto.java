package com.example.uni.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private Long bookId;
    private String userId;
    private String phoneNumber;
}

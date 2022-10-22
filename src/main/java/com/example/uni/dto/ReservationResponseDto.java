package com.example.uni.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    List<ReservationDto> reservationDtoList;
    List<BookDto> bookDtoList;
}

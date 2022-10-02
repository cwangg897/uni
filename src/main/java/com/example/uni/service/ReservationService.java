package com.example.uni.service;

import java.util.List;

import com.example.uni.dto.ReservationDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final BookService bookService;

    public void save(ReservationDto reservationDto, String userId) {
        bookService.changeStatus(reservationDto.getBookId(), BookStatus.RESERVATION);
        reservationMapper.save(reservationDto, userId);
    }

    // 요청받은거 판매자가 받은거 라서 등록한 책들의 아이디들을 추출하고 그중 요청테이블에서 조회하기
    public List<ReservationDto> getResponseAll(String userId) {
        return reservationMapper.getResponseAll(userId);
    }

    // 요청테이블에서 자기의 아이디로 검색
    public List<ReservationDto> getRequestAll(String userId) {
        return reservationMapper.getRequestAll(userId);
    }
}

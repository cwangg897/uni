package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.ReservationDto;
import com.example.uni.dto.ReservationResponseDto;
import com.example.uni.enums.UserType;
import com.example.uni.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    // 예약하기 post
    @LoginCheck(userType = UserType.USER)
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ReservationDto reservationDto, @SessionUserId String userId) {
        reservationService.save(reservationDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 요청받은 목록
    // 자기가 등록한 책들의 아이디에서 요청한 목록을 찾기
    @LoginCheck(userType = UserType.USER)
    @GetMapping("/response")
    public ResponseEntity<ReservationResponseDto> getResponseAll(@SessionUserId String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getResponseAll(userId));
    }

    // 요청한 목록
    // 예약테이블에서 요청한 사람의 아이디로 검색하기
    @LoginCheck(userType = UserType.USER)
    @GetMapping("/request")
    public ResponseEntity<ReservationResponseDto> getRequestAll(@SessionUserId String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getRequestAll(userId));
    }


}

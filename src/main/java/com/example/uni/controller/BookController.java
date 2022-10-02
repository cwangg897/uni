package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.UserType;
import com.example.uni.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // 책 판매 글 등록
    @LoginCheck(userType = UserType.ALL)
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BookDto bookDto, @SessionUserId String userId) {
        bookService.save(bookDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 상태 변경가능하게 해야겠다(책 아이디를 등록한 사람이랑 로그인한 유저랑같으면)
    // 글 변경
    @LoginCheck(userType = UserType.USER)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody BookDto bookDto, @PathVariable Long id) {
        bookService.update(bookDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 상태 변경가능하게 해야겠다(책 아이디를 등록한 사람이랑 로그인한 유저랑같으면)
    @LoginCheck(userType = UserType.ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 목록 다 보기
    @LoginCheck(userType = UserType.ALL)
    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    // 책 상세보기
    @LoginCheck(userType = UserType.ALL)
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    // status에 따라 보기
    @LoginCheck(userType = UserType.ALL)
    @GetMapping("/{id}")
    public ResponseEntity<List<BookDto>> findAllByStatus(@RequestParam("bookStatus")BookStatus status) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByStatus(status));
    }

    // 상태 변경가능하게 해야겠다(책 아이디를 등록한 사람이랑 로그인한 유저랑같으면)








}

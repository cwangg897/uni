package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.BookType;
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

    /**
     * 조교가 책 등록가능하다
     */
    @LoginCheck(userType = UserType.ADMIN)
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BookDto bookDto, @SessionUserId String userId) {
        bookService.save(bookDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @LoginCheck(userType = UserType.ADMIN)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody BookDto bookDto, @PathVariable Long id) {
        bookService.update(bookDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // STATUS 상태변경 WAIT -> 구입완료같은걸로 조교봔 조교가 승인되면 그러면되것네
    @LoginCheck(userType = UserType.ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //    @LoginCheck(userType = UserType.USER)
    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    // 학과별 책은 유저아이디로 ADMIN이 조교아이디가 학과별 책임

//    // 대기 완료
//    @GetMapping
//    public ResponseEntity<List<BookDto>> findAllByStatus(@RequestParam("status") BookStatus status) {
//        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByStatus(status));
//    }
//
//    // 빌리는지 파는지
//    @GetMapping
//    public ResponseEntity<List<BookDto>> findAllByStatus(@RequestParam("type") BookType type) {
//        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByType(type));
//    }


}

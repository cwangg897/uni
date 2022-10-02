package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.BookDto;
import com.example.uni.dto.SignUpDto;
import com.example.uni.dto.UserDto;
import com.example.uni.enums.UserType;
import com.example.uni.service.BookService;
import com.example.uni.service.LoginService;
import com.example.uni.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpDto userDto) {
        userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody UserDto userDto) {
        userService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @LoginCheck
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logoutUser() {
        loginService.logoutUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @LoginCheck
    @PatchMapping
    public ResponseEntity<Void> updateUser(@SessionUserId String userId, @RequestBody UserDto userDto) {
        userService.updateUser(userDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @LoginCheck
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@SessionUserId String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 작성한 글 목록 다 보기
    @LoginCheck(userType = UserType.USER)
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> findAllBook(@SessionUserId String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByUserId(userId));
    }

}

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


    @DeleteMapping("/logout")
    public ResponseEntity<Void> logoutUser() {
        loginService.logoutUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // LoginCheck에서 권한을 체크하는것을 찾아야겠네
    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestParam("userId") String userId, @RequestBody UserDto userDto) {
        userService.updateUser(userDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 작성한 글 목록 다 보기
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> findAllBook(@RequestParam("userId") String userId) {
        System.out.println("userId = " + userId);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<UserDto> myInfo(@RequestParam("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.myInfo(userId));
    }

}

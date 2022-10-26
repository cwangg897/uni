package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.BoardDto;
import com.example.uni.enums.UserType;
import com.example.uni.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
@Getter
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody BoardDto boardDto, @RequestParam("userId") String userId) {
        boardService.createBoard(boardDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> findAll() {
        List<BoardDto> list = boardService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto,
                                            @RequestParam("userId") String userId) {
        boardService.updateBoard(id, boardDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id, @RequestParam("userId") String userId) {
        boardService.deleteBoard(id, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> findById(@PathVariable Long id) {
        BoardDto boardDto = boardService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardDto);
    }

}

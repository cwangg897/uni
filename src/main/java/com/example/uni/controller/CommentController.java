package com.example.uni.controller;

import java.util.List;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.annotation.SessionUserId;
import com.example.uni.dto.BoardDto;
import com.example.uni.dto.CommentDto;
import com.example.uni.enums.UserType;
import com.example.uni.service.BoardService;
import com.example.uni.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @LoginCheck(userType = UserType.USER)
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto, @SessionUserId String userId) {
        commentService.createComment(commentDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAllByBoarId(@RequestParam(name = "boardId") Long boardId) {
        List<CommentDto> list = commentService.findAllByBoarId(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @LoginCheck(userType = UserType.USER)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto,
                                              @SessionUserId String userId) {
        commentService.updateComment(id, commentDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @LoginCheck(userType = UserType.USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @SessionUserId String userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

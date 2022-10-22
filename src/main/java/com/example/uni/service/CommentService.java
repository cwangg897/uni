package com.example.uni.service;

import java.util.List;

import com.example.uni.dto.CommentDto;
import com.example.uni.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    private void validAuthority(String commentUserId, String userId) {
        if (!commentUserId.equals(userId)) {
            throw new RuntimeException("글 권한이 없습니다.");
        }
    }

    public void createComment(CommentDto commentDto, String userId) {
        commentMapper.createComment(commentDto, userId);
    }

    // 게시판별 댓글찾기
    public List<CommentDto> findAllByBoarId(Long boardId) {
        return commentMapper.findAllByBoarId(boardId);
    }

    // valid
    public void updateComment(Long id, CommentDto commentDto, String userId) {
        validAuthority(commentDto.getUserId(), userId);
        commentMapper.updateComment(id, commentDto);
    }

    // valid
    public void deleteComment(Long id, String userId) {
        CommentDto findComment = commentMapper.findById(id);
        validAuthority(findComment.getUserId(), userId);
        commentMapper.deleteComment(id);
    }
}

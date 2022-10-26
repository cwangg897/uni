package com.example.uni.service;


import java.util.List;

import com.example.uni.dto.BoardDto;
import com.example.uni.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    private void validAuthority(String boarUserId, String userId) {
        if (!boarUserId.equals(userId)) {
            throw new RuntimeException("글 권한이 없습니다.");
        }
    }

    public void createBoard(BoardDto boardDto, String userId) {
        boardMapper.createBoard(boardDto, userId);
    }

    public List<BoardDto> findAll() {
        return boardMapper.findAll();
    }

    // 아이디 맞는지 체크
    public void updateBoard(Long id, BoardDto boardDto, String userId) {
        validAuthority(boardDto.getUserId(), userId);
        boardMapper.updateBoard(id, boardDto);
    }

    // 아이디맞는지 체크
    public void deleteBoard(Long id, String userId) {
        // 같은지
        BoardDto boardDto = boardMapper.findById(id);
        validAuthority(boardDto.getUserId(), userId);
        boardMapper.deleteBoard(id);
    }

    public BoardDto findById(Long id) {
        return boardMapper.findById(id);
    }
}

package com.example.uni.service;

import java.util.List;

import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;


    public void save(BookDto bookDto, String userId) {
        bookMapper.save(bookDto, userId);
    }

    // 정보 변경
    public void update(BookDto bookDto, Long id) {
        bookMapper.update(bookDto, id);
    }

    // 글삭제
    public void delete(Long id) {
        bookMapper.delete(id);
    }

    // 목록 다 보기
    public List<BookDto> findAll() {
        return bookMapper.findAll();
    }

    public BookDto findById(Long id) {
        return bookMapper.findById(id);
    }

    public List<BookDto> findAllByStatus(BookStatus status) {
        return bookMapper.findAllByStatus(status);
    }

    public List<BookDto> findAllByUserId(String userId) {
        return bookMapper.findAllByUserId(userId);
    }

    public void changeStatus(Long bookId, BookStatus status) {
        bookMapper.changeStatus(bookId, status);
    }
}

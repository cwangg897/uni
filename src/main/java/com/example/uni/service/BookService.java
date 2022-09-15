package com.example.uni.service;

import java.util.List;

import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.BookType;
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

    // 수정
    public void update(BookDto bookDto, Long id) {
        bookMapper.update(bookDto, id);
    }

    // 삭제
    public void delete(Long id) {
        bookMapper.delete(id);
    }

    // STATUS는 완료, 미완료
    public void changeStatus(BookStatus status, Long id) {
        bookMapper.changeStatus(status, id);
    }

    public List<BookDto> findAll() {
        return bookMapper.findAll();
    }

    public List<BookDto> findAllByStatus(BookStatus status) {
        return bookMapper.findAllByStatus(status);
    }

    public List<BookDto> findAllByType(BookType type) {
        return bookMapper.findAllByType(type);
    }
}

package com.example.uni.mapper;

import java.util.List;

import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {

    void save(@Param("book") BookDto bookDto, @Param("userId") String userId);

    void update(@Param("book") BookDto bookDto, @Param("id") Long id);

    void delete(Long id);

    List<BookDto> findAll();

    BookDto findById(Long id);

    List<BookDto> findAllByStatus(BookStatus status);

    List<BookDto> findAllByUserId(String userId);

    void changeStatus(@Param("bookId") Long bookId, @Param("status") BookStatus status);
}

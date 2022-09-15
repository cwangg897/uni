package com.example.uni.mapper;

import java.util.List;

import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {
    void save(@Param("book") BookDto bookDto, @Param("userId") String userId);


    void update(@Param("book") BookDto bookDto, @Param("id") Long id);

    void delete(Long id);

    // 처음에는 이미지가 적으니까 많아보이게 상태가 WAIT, COMPLETE다 보여주는것으로
    List<BookDto> findAll();


    List<BookDto> findAllByStatus(BookStatus status);

    List<BookDto> findAllByType(BookType type);

    // 상태 변경 완료, 미완료
    void changeStatus(@Param("status") BookStatus status, @Param("id") Long id);
}

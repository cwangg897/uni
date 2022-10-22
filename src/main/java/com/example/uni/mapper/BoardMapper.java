package com.example.uni.mapper;

import java.util.List;

import com.example.uni.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface BoardMapper {

    void createBoard(@Param("board") BoardDto boardDto, @Param("userId") String userId);

    List<BoardDto> findAll();

    void updateBoard(@Param("id") Long id, @Param("board") BoardDto boardDto);

    BoardDto findById(@Param("id") Long id);

    void deleteBoard(@Param("id") Long id);
}

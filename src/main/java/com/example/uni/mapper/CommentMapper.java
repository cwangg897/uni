package com.example.uni.mapper;

import java.util.List;

import com.example.uni.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    void createComment(@Param("comment") CommentDto commentDto, @Param("userId") String userId);

    List<CommentDto> findAllByBoarId(@Param("boardId") Long boardId);

    void updateComment(@Param("id") Long id, @Param("comment") CommentDto commentDto);

    void deleteComment(@Param("id") Long id);

    CommentDto findById(@Param("id") Long id);
}

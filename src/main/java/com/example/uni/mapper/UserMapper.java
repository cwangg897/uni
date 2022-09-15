package com.example.uni.mapper;

import com.example.uni.dto.SignUpDto;
import com.example.uni.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(@Param("user") SignUpDto userDto);

    Optional<UserDto> isExists(String id);

    boolean idExists(String id);

    UserDto findById(String id);

    void update(@Param("user") UserDto userDto ,@Param("userId") String id);

    void delete(String id);

}

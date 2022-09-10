package com.example.uni.service;

import com.example.uni.dto.SignUpDto;
import com.example.uni.dto.UserDto;
import com.example.uni.exception.UserException;
import com.example.uni.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final LoginService loginService;

    public void save(SignUpDto userDto) {
        if (userMapper.idExists(userDto.getId())) {
            throw new UserException("이미 존재하는 아이디입니다", HttpStatus.BAD_REQUEST);
        }
        userMapper.save(userDto);
    }


    public void login(UserDto userDto){
        if (!userMapper.idExists(userDto.getId())) {
            throw new UserException("존재하지 않는 아이디입니다", HttpStatus.NOT_FOUND);
        }

        UserDto findUser = userMapper.findById(userDto.getId());

        if (!userDto.getPassword().equals(findUser.getPassword()) ) {
            throw new UserException("아이디랑 비밀번호를 확인해주세요", HttpStatus.BAD_REQUEST);
        }
        loginService.loginUser(findUser.getId(), findUser.getType());
    }

    public void updateUser(UserDto userDto, String id){
        userMapper.update(userDto, id);
    }

    public void deleteUser(String userId){
        userMapper.delete(userId);
    }



}

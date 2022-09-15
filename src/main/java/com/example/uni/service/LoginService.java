package com.example.uni.service;

import com.example.uni.dto.UserDto;
import com.example.uni.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession httpSession;
    private final String SESSION_ID = "SESSION_ID";
    private final String USER_TYPE = "USER_TYPE";

    public void loginUser(UserDto userDto) {
        httpSession.setAttribute(SESSION_ID, userDto.getId());
        httpSession.setAttribute(USER_TYPE, userDto.getType());
    }

    public String getSessionUserId() {
        return (String) httpSession.getAttribute(SESSION_ID);
    }

    public UserType getUserType() {
        return (UserType) httpSession.getAttribute(USER_TYPE);
    }


    public void logoutUser() {
        httpSession.removeAttribute(SESSION_ID);
        httpSession.removeAttribute(USER_TYPE);
    }


}

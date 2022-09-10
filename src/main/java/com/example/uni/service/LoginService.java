package com.example.uni.service;

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

    public void loginUser(String id, UserType userType) {
        httpSession.setAttribute(SESSION_ID, id);
        httpSession.setAttribute(USER_TYPE, userType);
    }

    public String getSessionUserId() {
        return (String)httpSession.getAttribute(SESSION_ID);
    }


    public void logoutUser() {
        httpSession.removeAttribute(SESSION_ID);
        httpSession.removeAttribute(USER_TYPE);
    }

    public UserType getUserType(){
        return (UserType) httpSession.getAttribute(USER_TYPE);
    }


}

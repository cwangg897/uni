package com.example.uni.aop;

import javax.servlet.http.HttpServletRequest;

import com.example.uni.annotation.LoginCheck;
import com.example.uni.enums.UserType;
import com.example.uni.exception.UserException;
import com.example.uni.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final LoginService loginService;

    @Before("@annotation(com.example.uni.annotation.LoginCheck) && @annotation(target)")
    public void sessionLoginCheck(LoginCheck target) {
        String sessionUserId = loginService.getSessionUserId();
        System.out.println("===============");
        System.out.println("sessionUserId = " + sessionUserId);
        System.out.println("loginService = " + loginService.getUserType());
        System.out.println("===============");

        if (sessionUserId == null) {
            throw new UserException("유효하지않는 세션입니다", HttpStatus.UNAUTHORIZED);
        }

        UserType targetUserType = target.userType();

        if (targetUserType == UserType.ALL) {
            return;
        }

        UserType userType = loginService.getUserType();

        if (userType != targetUserType) {
            throw new UserException("권한없습니다", HttpStatus.FORBIDDEN);
        }
    }


}

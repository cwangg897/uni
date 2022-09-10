package com.example.uni.controller;

import com.example.uni.dto.SignUpDto;
import com.example.uni.dto.UserDto;
import com.example.uni.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    public SignUpDto getSignUpDto(){
        return SignUpDto.builder()
                .id("user1")
                .password("1234")
                .name("최왕규")
                .email("dhkdrb897@naver.com")
                .studentId("20174376")
                .phoneNumber("010-1234-1234")
                .build();
    }


//    @BeforeEach
//    void beforeEach(){
//        mockHttpSession.setAttribute("SESSION_ID", "user1");
//        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);
//    }

    @Test
    void signUp() throws Exception{
        String json = objectMapper.writeValueAsString(getSignUpDto());
        mockMvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    // 존재 안하고 하면 CLIENT_ERROR
    @Test
    void login() throws Exception{
        UserDto userDto = UserDto.builder().id("user1").password("1234").build();
        String json = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/users/login").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


}
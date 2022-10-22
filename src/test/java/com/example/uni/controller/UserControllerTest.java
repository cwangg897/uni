package com.example.uni.controller;

import com.example.uni.EnableMockMvc;
import com.example.uni.dto.SignUpDto;
import com.example.uni.dto.UserDto;
import com.example.uni.enums.UserType;
import com.example.uni.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@EnableMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    public SignUpDto getSignUpDto() {
        return SignUpDto.builder()
                .id("user3")
                .password("1234")
                .name("이름3")
                .email("user3@naver.com")
                .studentId("20173333")
                .phoneNumber("010-3333-3333")
                .type(UserType.ADMIN)
                .build();
    }


    @Test
    void signUp() throws Exception {
        String json = objectMapper.writeValueAsString(getSignUpDto());
        mockMvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    // 존재 안하고 하면 CLIENT_ERROR
    @Test
    void login() throws Exception {
        UserDto userDto = UserDto.builder().id("user1").password("1234").build();
        String json = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/users/login").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void logout() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(delete("/users/logout").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());


        Assertions.assertNull(mockHttpSession.getAttribute("SESSION_ID"));
        Assertions.assertNull(mockHttpSession.getAttribute("USER_TYPE"));
    }

    @Test
    void updateUser(@Autowired UserMapper userMapper) throws Exception {
        String userId = "user1";
        mockHttpSession.setAttribute("SESSION_ID", userId);
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        UserDto userDto = UserDto.builder().id("user1").name("홍길동").email("이메일123").studentId("1234").phoneNumber("1233444").build();
        String json = objectMapper.writeValueAsString(userDto);

        UserDto before = userMapper.findById(userId);

        mockMvc.perform(patch("/users").session(mockHttpSession)
                        .content(json).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        UserDto after = userMapper.findById(userId);
        Assertions.assertNotEquals(before.getName(), after.getName());
    }

    @Test
    void deleteUser(@Autowired UserMapper userMapper) throws Exception {
        String userId = "user1";
        mockHttpSession.setAttribute("SESSION_ID", userId);
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(delete("/users").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        UserDto findById = userMapper.findById(userId);
        Assertions.assertNull(findById);
    }

    @Test
    void findAllBook() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user2");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/users/books").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


}
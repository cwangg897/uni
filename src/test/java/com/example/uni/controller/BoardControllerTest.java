package com.example.uni.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.uni.EnableMockMvc;
import com.example.uni.dto.BoardDto;
import com.example.uni.dto.BookDto;
import com.example.uni.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@EnableMockMvc
@SpringBootTest
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    String title = "제목2";
    String content = "내용입니다2";
    String userId = "user1";

    private BoardDto getBoardDto(){
        return BoardDto.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }


    @Test
    void save() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getBoardDto());
        mockMvc.perform(post("/boards").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        title = "제목수정1";
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getBoardDto());
        mockMvc.perform(patch("/boards/1").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);
        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/1").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/boards").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findById() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/board/1").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
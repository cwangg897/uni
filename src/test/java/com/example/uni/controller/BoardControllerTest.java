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
import org.springframework.transaction.annotation.Transactional;

@EnableMockMvc
@SpringBootTest
@Transactional
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



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

        String json = objectMapper.writeValueAsString(getBoardDto());
        mockMvc.perform(post("/boards").param("userId", userId).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        title = "제목수정1";
        String json = objectMapper.writeValueAsString(getBoardDto());
        mockMvc.perform(patch("/boards/7").param("userId", "user6").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/7").param("userId", userId))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception{
        mockMvc.perform(get("/boards"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findById() throws Exception{
        mockMvc.perform(get("/boards/7"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
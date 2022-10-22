package com.example.uni.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.uni.EnableMockMvc;
import com.example.uni.dto.CommentDto;
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
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    String content = "내용3";
    Long boardId = 2L;
    String userId = "user1";

    private CommentDto getCommentDto(){
        return CommentDto.builder()
                .content(content)
                .boardId(boardId)
                .userId(userId)
                .build();
    }

    @Test
    void save() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getCommentDto());
        mockMvc.perform(post("/comments").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        content = "댓글내용3";
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getCommentDto());
        mockMvc.perform(patch("/comments/4").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);
        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/2").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAllByBoarId() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/comments").session(mockHttpSession).param("boardId", "2"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
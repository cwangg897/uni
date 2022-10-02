package com.example.uni.controller;

import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    private BookDto getBookDto(){
        return BookDto.builder()
                .name("이름2")
                .content("내용2")
                .author("저자2")
                .image("이미지2")
                .price(10000L)
                .publisher("출판사2")
                .publicationDate("출판날짜2")
                .status(BookStatus.SELL)
                .build();
    }


    @Test
    void save() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "computer");
        mockHttpSession.setAttribute("USER_TYPE", UserType.ADMIN);

        String json = objectMapper.writeValueAsString(getBookDto());
        mockMvc.perform(post("/books").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void update() {



    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByStatus() {
    }

    @Test
    void testFindAllByStatus() {
    }
}
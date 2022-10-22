package com.example.uni.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.uni.EnableMockMvc;
import com.example.uni.dto.ReservationDto;
import com.example.uni.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@EnableMockMvc
class ReservationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    Long id  = 1L;
    Long bookId = 3L;
    String userId = "user3"; // user2만들고 테스트하기
    String phoneNumber = "010-3333-3333";

    public ReservationDto getReservationDto(){
        return ReservationDto.builder()
                .id(id)
                .bookId(bookId)
                .userId(userId)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Test
    void save() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user3");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getReservationDto());

        mockMvc.perform(post("/reservations").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getResponseAll() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/reservations/response").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getRequestAll() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user3");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/reservations/request").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
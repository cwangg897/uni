package com.example.uni.controller;


import com.example.uni.EnableMockMvc;
import com.example.uni.dto.BookDto;
import com.example.uni.enums.BookStatus;
import com.example.uni.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMockMvc
@SpringBootTest
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession mockHttpSession = new MockHttpSession();

    String name = "책이름2";
    String title = "제목";
    String content = "내용";
    String author = "저자";
    String image = "이미지2";
    Long price = 12000L;
    String publisher = "출판사";
    String publicationDate = "출판날짜";
    BookStatus status = BookStatus.LEND;
    String userId = "user1";

    private BookDto getBookDto() {
        return BookDto.builder()
                .name(name)
                .title(title)
                .content(content)
                .author(author)
                .image(image)
                .price(price)
                .publisher(publisher)
                .publicationDate(publicationDate)
                .status(status)
                .userId(userId)
                .build();
    }


    @Test
    void save() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getBookDto());
        mockMvc.perform(post("/books").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        name = "이름 수정임";
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        String json = objectMapper.writeValueAsString(getBookDto());
        mockMvc.perform(patch("/books/9").session(mockHttpSession).content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);
        BookDto bookDto = BookDto.builder().userId("user1").build();
        String json = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/9").session(mockHttpSession)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/books").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    void findById() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/books/10").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAllByStatus() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/books/status/SELL").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void changeStatus() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        BookDto bookDto = BookDto.builder().userId("user1").status(BookStatus.SELL).build();
        String json = objectMapper.writeValueAsString(bookDto);


        mockMvc.perform(patch("/books/10/status").session(mockHttpSession)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findAllSearch() throws Exception{
        mockHttpSession.setAttribute("SESSION_ID", "user1");
        mockHttpSession.setAttribute("USER_TYPE", UserType.USER);

        mockMvc.perform(get("/books/title/제목").session(mockHttpSession))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


}
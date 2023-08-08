package com.company.bookstore.controller;


import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    private ObjectMapper mapper = new ObjectMapper();



    @Test
    public void shouldGetBooks() throws Exception {

        // ACT
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetBookById() throws Exception {

        // ACT
        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldAddBook() throws Exception {

        // ARRANGE
        Book book = new Book();
        // book.setPublishDate(LocalDate.of(2011, 05, 1));
        book.setTitle("testBook");
        book.setPrice(new BigDecimal("1.11"));
        book.setIsbn("111-1-11-111111-1");
        book.setAuthorId(1);
        book.setPublisherId(1);


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book);

        // ACT
        mockMvc.perform(
                        post("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldUpdateBook() throws Exception {

        // ARRANGE
        Book book = new Book();
        book.setId(1);
        // book.setPublishDate(LocalDate.of(2011, 05, 1));
        book.setTitle("testBook");
        book.setPrice(new BigDecimal("2.22"));
        book.setIsbn("222-1-11-111111-1");
        book.setAuthorId(1);
        book.setPublisherId(1);


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book);

        // ACT
        mockMvc.perform(
                        put("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldDeleteBook() throws Exception {

        mockMvc.perform(delete("/book/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldGetBookByAuthorId() throws Exception {

        // ACT
        mockMvc.perform(get("/books/books/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
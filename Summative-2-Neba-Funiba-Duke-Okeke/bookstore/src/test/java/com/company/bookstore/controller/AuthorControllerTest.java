package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthorRepository authorRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetAuthors() throws Exception {

        // ACT
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetAuthorById() throws Exception {

        // ACT
        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldAddAuthor() throws Exception {

        // ARRANGE
        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("New York");
        author.setStreet("Author lane");


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(author);

        // ACT
        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldUpdateAuthor() throws Exception {

        // ARRANGE
        Author author = new Author();
        author.setCity("New York");
        author.setEmail("jd@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("Jane");
        author.setLastName("Doe");
        author.setPostalCode("22222");
        author.setState("New York");
        author.setStreet("Author lane");


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(author);

        // ACT
        mockMvc.perform(
                        put("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldDeleteAuthor() throws Exception {

        mockMvc.perform(delete("/author/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}
package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PublisherRepository publisherRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetPublishers() throws Exception {

        // ACT
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetPublisherById() throws Exception {

        // ACT
        mockMvc.perform(get("/publishers/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldAddPublisher() throws Exception {

        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setCity("pubCity");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGraw Hill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("Virginia");
        publisher.setStreet("P Lane");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(publisher);

        // ACT
        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldUpdatePublisher() throws Exception {

        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setCity("pubCity");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGraw Hill");
        publisher.setPhone("222-222-2222");
        publisher.setPostalCode("22222");
        publisher.setState("Virginia");
        publisher.setStreet("Harvey Lane");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(publisher);

        // ACT
        mockMvc.perform(
                        put("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldDeletePublisher() throws Exception {

        mockMvc.perform(delete("/publisher/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}
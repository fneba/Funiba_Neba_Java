package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAddCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(customer);

        // ACT
        mockMvc.perform(
                        post("/customers")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetCustomerByID() throws Exception {

        // ACT
        mockMvc.perform(get("/customers/1"))                            // Perform the GET request
                .andDo(print())                                                     // Print results to console
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateCustomer() throws Exception {

        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 4A");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(customer);

        // ACT
        mockMvc.perform(
                        put("/customers")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().is(204));

    }

    @Test
    public void shouldDeleteCustomer() throws Exception {

        mockMvc.perform(delete("/customers/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFilterCustomersByState() throws Exception {

        mockMvc.perform(get("/customers/state/Maryland"))                            // Perform the GET request
                .andDo(print())                                                     // Print results to console
                .andExpect(status().isOk());

    }

}
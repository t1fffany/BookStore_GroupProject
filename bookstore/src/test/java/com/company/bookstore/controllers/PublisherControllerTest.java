package com.company.bookstore.controllers;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTest {

    @MockBean
    private PublisherRepository publisherRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        publisherRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreatePublisher() throws Exception {
        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        Mockito.when(publisherRepository.save(Mockito.any(Publisher.class)))
                .thenReturn(publisher);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/publisher")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(publisher)))  // Request payload as JSON
                .andDo(print())  // Print results to console
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetPublisherById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/publisher/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllPublishers() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/publisher/publishers")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updatePublisherTest() throws Exception {
        //ARRANGE
        Publisher publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        Mockito.when(publisherRepository.save(Mockito.any(Publisher.class)))
                .thenReturn(publisher);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/publisher", publisher.getId())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(publisher)))
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deletePublisherTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/publisher/1"))  // Perform the delete request
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

}

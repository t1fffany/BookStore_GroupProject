package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.repositories.AuthorRepository;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        authorRepository.deleteAll();
    }

    // Create
    @Test
    public void testCreateAuthor() throws Exception {
        // ARRANGE
        Author author = new Author();
        author.setFirstName("First");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail.gmail.com");

        Mockito.when(authorRepository.save(Mockito.any(Author.class)))
                .thenReturn(author);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/author")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(author)))  // Request payload as JSON
                .andDo(print())  // Print results to console
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetAuthorById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/author/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllAuthors() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/author/authors")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateAuthorTest() throws Exception {
        //ARRANGE
        Author author = new Author();
        author.setFirstName("First");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail.gmail.com");

        Mockito.when(authorRepository.save(Mockito.any(Author.class)))
                .thenReturn(author);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/author", author.getId())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(author)))
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deleteAuthorTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/author/1"))  // Perform the delete request
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }


}
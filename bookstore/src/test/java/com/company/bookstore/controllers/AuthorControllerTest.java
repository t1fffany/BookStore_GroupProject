package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.repositories.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

        @MockBean
        private AuthorRepository authorRepository;
        @Autowired
        private MockMvc mockMvc;


        // Testing get ALL "/authors"
        @Test
        public void testGetAll() throws Exception {
            // ARRANGE
            mockMvc.perform(
                            get("/author")   // Perform the GET request
                                    .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                    .andDo(print()) // Print results to console
                    .andExpect(status().isOk());
        }

        // Testing get "/author/{id}"
        @Test
        public void testGetById() throws Exception {

            // ACT /author/{id}
            mockMvc.perform(
                            get("/author/1")   // Perform the GET request
                                    .contentType(MediaType.APPLICATION_JSON))   // Tell the server it's in JSON format
                    .andDo(print()) // Print results to console
                    .andExpect(status().isOk());
        }



        // Testing POST
        @Test
        public void newAuthorTest() throws Exception {
            //ARRANGE
            Author author = new Author();
            author.setId(1);
            author.setFirstName("John");
            author.setLastName("Doe");
            author.setStreet("10 Green St");
            author.setCity("San Francisco");
            author.setState("California");
            author.setPostalCode("94123");
            author.setPhone("415-555-0089");
            author.setEmail("johnDoe@me.com");


            Mockito.when(authorRepository.save(Mockito.any(Author.class)))
                    .thenReturn(author);

            ObjectMapper objectMapper = new ObjectMapper();
            // ACT
            mockMvc.perform(
                            post("/author")                          // Perform the POST request
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(author)))    // Request payload as JSON
                    .andDo(print())                             // Print results to console
                    .andExpect(status().isCreated());  // 201 code

        }

        // Testing Put
        @Test
        public void updateAuthorTest() throws Exception {
            //ARRANGE
            Author author = new Author();
            author.setId(2);
            author.setFirstName("Boo");
            author.setLastName("Doe");
            author.setStreet("10 Green St");
            author.setCity("San Francisco");
            author.setState("California");
            author.setPostalCode("94123");
            author.setPhone("415-555-0089");
            author.setEmail("johnDoe@me.com");

            Mockito.when(authorRepository.save(Mockito.any(Author.class)))
                    .thenReturn(author);

            ObjectMapper objectMapper = new ObjectMapper();

            // ACT
            mockMvc.perform(
                            put("/author/update/{id}", author.getId())               // Perform the PUT request
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(author)))
                    .andDo(print())                          // Print results to console
                    .andExpect(status().isNoContent());             // ASSERT (status code is 204)

        }

        // Testing delete /author
        @Test
        public void deleteAuthorTest() throws Exception {
            // ACT
            mockMvc.perform(
                            delete("/author/1")               // Perform the delete request
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())                          // Print results to console
                    .andExpect(status().isNoContent());             // ASSERT (status code is 204)

        }
}

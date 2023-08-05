package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private PublisherRepository publisherRepository;
    @Autowired
    private MockMvc mockMvc;

    Author author;
    Publisher publisher;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        author = new Author();
        author.setFirstName("First");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail.gmail.com");
    }

    // Create
    @Test
    public void testCreateBook() throws Exception {
        // ARRANGE
        Book book = new Book();
        book.setIsbn("1");
        // ADVISED BY DALONTE, CONFRIMED BY NATEYANA: we can comment out setPublishDate() to resolve LocalDate dependency error
        // book.setPublishDate(LocalDate.of(2022, 10, 1));
        book.setAuthorId(this.author.getId());
        book.setTitle("Netflix Original");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("23.45"));

        Mockito.when(bookRepository.save(Mockito.any(Book.class)))
                .thenReturn(book);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        post("/book")  // Perform the POST request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(book)))  // Request payload as JSON
                .andDo(print())  // Print results to console
                .andExpect(status().isCreated());  // 201 code
    }


    // Read By ID
    @Test
    public void testGetBookById() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/book/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Read All
    @Test
    public void testGetAllBooks() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/book/books")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }

    // Update
    @Test
    public void updateBookTest() throws Exception {
        //ARRANGE
        Book book = new Book();
        book.setIsbn("1");
        // ADVISED BY DALONTE, CONFRIMED BY NATEYANA: we can comment out setPublishDate() to resolve LocalDate dependency error
        // book.setPublishDate(LocalDate.of(2022, 10, 1));
        book.setAuthorId(this.author.getId());
        book.setTitle("Netflix Original");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("23.45"));

        Mockito.when(bookRepository.save(Mockito.any(Book.class)))
                .thenReturn(book);

        ObjectMapper objectMapper = new ObjectMapper();

        // ACT
        mockMvc.perform(
                        put("/book", book.getId())  // Perform the PUT request
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(book)))
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }

    // Delete by ID
    @Test
    public void deleteBookTest() throws Exception {
        // ACT
        mockMvc.perform(
                        delete("/book/1"))  // Perform the delete request
                .andDo(print())  // Print results to console
                .andExpect(status().isNoContent());  // ASSERT (status code is 204)
    }


    // Search Book by Author ID
    @Test
    public void testGetBookByAuthorId() throws Exception {
        // ACT
        mockMvc.perform(
                        get("/book/author/1")  // Perform the GET request
                                .contentType(MediaType.APPLICATION_JSON))  // Tell the server it's in JSON format
                .andDo(print())  // Print results to console
                .andExpect(status().isOk());
    }
}
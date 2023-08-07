package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();


    }

    // Create
    @Test
    public void shouldAddAuthor() {
        // Arrange
        Author author = new Author();
        author.setFirstName("First");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail.gmail.com");

        // Act
        author = authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());

        // Assert
        assertEquals(author, author1.get());
    }

    // Read By ID
    @Test
    public void shouldGetAuthorById() {
        // Arrange
        Author author1 = new Author();
        author1.setFirstName("a1");
        author1.setLastName("Last");
        author1.setStreet("1st Street");
        author1.setCity("New York City");
        author1.setState("ny");
        author1.setPostalCode("12345");
        author1.setPhone("4041231234");
        author1.setEmail("ouremail.gmail.com");

        Author author2 = new Author();
        author2.setFirstName("a2");
        author2.setLastName("Last");
        author2.setStreet("1st Street");
        author2.setCity("New York City");
        author2.setState("ny");
        author2.setPostalCode("12345");
        author2.setPhone("4041231234");
        author2.setEmail("ouremail.gmail.com");

        // Act
        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        Optional<Author> foundAuthor = authorRepository.findById(author1.getId());

        // Assert
        assertEquals(foundAuthor.get(), author1);
    }


    // Read All
    @Test
    public void shouldGetAllAuthors() {
        // Arrange
        Author author1 = new Author();
        author1.setFirstName("a1");
        author1.setLastName("Last");
        author1.setStreet("1st Street");
        author1.setCity("New York City");
        author1.setState("ny");
        author1.setPostalCode("12345");
        author1.setPhone("4041231234");
        author1.setEmail("ouremail.gmail.com");

        Author author2 = new Author();
        author2.setFirstName("a2");
        author2.setLastName("Last");
        author2.setStreet("1st Street");
        author2.setCity("New York City");
        author2.setState("ny");
        author2.setPostalCode("12345");
        author2.setPhone("4041231234");
        author2.setEmail("ouremail.gmail.com");

        // Act
        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        List<Author> foundAuthors = authorRepository.findAll();

        // Assert
        assertEquals(2, foundAuthors.size());
        assertTrue(foundAuthors.contains(author1));
        assertTrue(foundAuthors.contains(author2));
    }


    // Update
    @Test
    public void shouldUpdateAuthor() {
        // Arrange
        Author author = new Author();
        author.setFirstName("a1");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail@gmail.com");

        author = authorRepository.save(author);

        author.setFirstName("a2");
        author.setLastName("Last 2");
        author.setStreet("2nd Street");
        author.setCity("Atlanta");
        author.setState("GA");
        author.setPostalCode("54321");
        author.setPhone("6781234567");
        author.setEmail("email2@gmail.com");

        author = authorRepository.save(author);

        // Act
        Optional<Author> author1 = authorRepository.findById(author.getId());

        // Assert
        assertEquals(author1.get(), author);
    }


    // Delete by ID
    @Test
    public void shouldDeleteAuthorById() {
        // Arrange
        Author author = new Author();
        author.setFirstName("First");
        author.setLastName("Last");
        author.setStreet("1st Street");
        author.setCity("New York City");
        author.setState("ny");
        author.setPostalCode("12345");
        author.setPhone("4041231234");
        author.setEmail("ouremail.gmail.com");

        author = authorRepository.save(author);

        // Act
        Optional<Author> foundAuthor = authorRepository.findById(author.getId());

        // Assert
        assertTrue(foundAuthor.isPresent());
        assertEquals(foundAuthor.get(), author);

        // Act
        authorRepository.deleteById(author.getId());

        foundAuthor = authorRepository.findById(author.getId());

        // Assert
        assertFalse(foundAuthor.isPresent());
    }
}
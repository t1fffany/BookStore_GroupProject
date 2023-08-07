package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    @BeforeEach
    public void setUp() throws Exception {
        //bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    // Create
    @Test
    public void shouldAddAuthor() {
        // Arrange
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

        Author authorTwo = new Author();
        authorTwo.setId(2);
        authorTwo.setFirstName("Boo");
        authorTwo.setLastName("Djorn");
        authorTwo.setStreet("4 Blueview St");
        authorTwo.setCity("Sacramento");
        authorTwo.setState("California");
        authorTwo.setPostalCode("95089");
        authorTwo.setPhone("415-555-0089");
        authorTwo.setEmail("nbooDjorn@me.com");

        // Act
        author = authorRepository.save(author);
        authorTwo = authorRepository.save(authorTwo);
        Optional<Author> findAuthor = authorRepository.findById(author.getId());

        // Assert
        assertEquals(findAuthor.get(), author);
    }


    // Read All
    @Test
    public void shouldGetAllPublishers() {
        // Arrange
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

        Author authorTwo = new Author();
        authorTwo.setId(2);
        authorTwo.setFirstName("Boo");
        authorTwo.setLastName("Djorn");
        authorTwo.setStreet("4 Blueview St");
        authorTwo.setCity("Sacramento");
        authorTwo.setState("California");
        authorTwo.setPostalCode("95089");
        authorTwo.setPhone("415-555-0089");
        authorTwo.setEmail("nbooDjorn@me.com");

        // Act
        author = authorRepository.save(author);
        authorTwo = authorRepository.save(authorTwo);
        List<Author> findAuthors = authorRepository.findAll();

        // Assert
        assertEquals(2, findAuthors.size());
        assertTrue(findAuthors.contains(author));
        assertTrue(findAuthors.contains(authorTwo));
    }


    // Update
    @Test
    public void shouldUpdatePublisher() {
        // Arrange
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

        author = authorRepository.save(author);

        author.setFirstName("Boo");
        author.setLastName("Djorn");
        author.setStreet("4 Blueview St");
        author.setCity("Sacramento");
        author.setState("California");
        author.setPostalCode("95089");
        author.setPhone("415-555-0089");
        author.setEmail("nbooDjorn@me.com");

        author = authorRepository.save(author);

        // Act
        Optional<Author> author1 = authorRepository.findById(author.getId());

        // Assert
        assertEquals(author1.get(), author);
    }


    // Delete by ID
    @Test
    public void shouldDeletePublisherById() {
        // Arrange
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

        author = authorRepository.save(author);

        // Act
        Optional<Author> findAuthor = authorRepository.findById(author.getId());

        // Assert
        assertTrue(findAuthor.isPresent());
        assertEquals(findAuthor.get(), author);

        // Act
        authorRepository.deleteById(author.getId());

        findAuthor = authorRepository.findById(author.getId());

        // Assert
        assertFalse(findAuthor.isPresent());
    }
}

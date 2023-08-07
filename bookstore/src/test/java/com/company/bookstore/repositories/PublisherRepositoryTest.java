package com.company.bookstore.repositories;

import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    BookRepository bookRepository;


    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    // Create
    @Test
    public void shouldAddPublisher() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        // Act
        publisher = publisherRepository.save(publisher);

        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        // Assert
        assertEquals(publisher, publisher1.get());
    }

    // Read By ID
    @Test
    public void shouldGetPublisherById() {
        // Arrange
        Publisher publisher1 = new Publisher();
        publisher1.setName("New York Books 1");
        publisher1.setStreet("1st Street");
        publisher1.setCity("New York City");
        publisher1.setState("ny");
        publisher1.setPostalCode("12345");
        publisher1.setPhone("4041231234");
        publisher1.setEmail("ouremail.gmail.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("New York Books 2");
        publisher2.setStreet("1st Street");
        publisher2.setCity("New York City");
        publisher2.setState("ny");
        publisher2.setPostalCode("12345");
        publisher2.setPhone("4041231234");
        publisher2.setEmail("ouremail.gmail.com");

        // Act
        publisher1 = publisherRepository.save(publisher1);
        publisher2 = publisherRepository.save(publisher2);
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher1.getId());

        // Assert
        assertEquals(foundPublisher.get(), publisher1);
    }


    // Read All
    @Test
    public void shouldGetAllPublishers() {
        // Arrange
        Publisher publisher1 = new Publisher();
        publisher1.setName("New York Books 1");
        publisher1.setStreet("1st Street");
        publisher1.setCity("New York City");
        publisher1.setState("ny");
        publisher1.setPostalCode("12345");
        publisher1.setPhone("4041231234");
        publisher1.setEmail("ouremail.gmail.com");

        Publisher publisher2 = new Publisher();
        publisher2.setName("New York Books 2");
        publisher2.setStreet("1st Street");
        publisher2.setCity("New York City");
        publisher2.setState("ny");
        publisher2.setPostalCode("12345");
        publisher2.setPhone("4041231234");
        publisher2.setEmail("ouremail.gmail.com");

        // Act
        publisher1 = publisherRepository.save(publisher1);
        publisher2 = publisherRepository.save(publisher2);
        List<Publisher> foundPublishers = publisherRepository.findAll();

        // Assert
        assertEquals(2, foundPublishers.size());
        assertTrue(foundPublishers.contains(publisher1));
        assertTrue(foundPublishers.contains(publisher2));
    }


    // Update
    @Test
    public void shouldUpdatePublisher() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        publisher = publisherRepository.save(publisher);

        publisher.setName("Atlanta Books");
        publisher.setStreet("2nd Street");
        publisher.setCity("Atlanta");
        publisher.setState("GA");
        publisher.setPostalCode("22345");
        publisher.setPhone("5041231234");
        publisher.setEmail("myemail.gmail.com");

        publisher = publisherRepository.save(publisher);

        // Act
        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        // Assert
        assertEquals(publisher1.get(), publisher);
    }


    // Delete by ID
    @Test
    public void shouldDeletePublisherById() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("New York Books");
        publisher.setStreet("1st Street");
        publisher.setCity("New York City");
        publisher.setState("ny");
        publisher.setPostalCode("12345");
        publisher.setPhone("4041231234");
        publisher.setEmail("ouremail.gmail.com");

        publisher = publisherRepository.save(publisher);

        // Act
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher.getId());

        // Assert
        assertTrue(foundPublisher.isPresent());
        assertEquals(foundPublisher.get(), publisher);

        // Act
        publisherRepository.deleteById(publisher.getId());

        foundPublisher = publisherRepository.findById(publisher.getId());

        // Assert
        assertFalse(foundPublisher.isPresent());
    }
}

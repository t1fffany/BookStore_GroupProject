package com.company.bookstore.repositories;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

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


        authorRepository.save(author);
        publisherRepository.save(publisher);
    }

    // Create
    @Test
    public void shouldAddBook() {
        // Arrange
        Book book = new Book();
        book.setIsbn("1");
        book.setPublishDate(LocalDate.of(2022, 10, 1));
        book.setAuthorId(this.author.getId());
        book.setTitle("Netflix Original");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("23.45"));

        // Act
        book = bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());

        // Assert
        assertEquals(book, book1.get());
    }

    // Read By ID
    @Test
    public void shouldGetBookById() {
        // Arrange
        Book book1 = new Book();
        book1.setIsbn("1");
        book1.setPublishDate(LocalDate.of(2022, 10, 1));
        book1.setAuthorId(this.author.getId());
        book1.setTitle("Netflix Original");
        book1.setPublisherId(this.publisher.getId());
        book1.setPrice(new BigDecimal("23.45"));

        Book book2 = new Book();
        book2.setIsbn("1");
        book2.setPublishDate(LocalDate.of(2022, 10, 1));
        book2.setAuthorId(this.author.getId());
        book2.setTitle("Netflix Original");
        book2.setPublisherId(this.publisher.getId());
        book2.setPrice(new BigDecimal("23"));

        // Act
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        Optional<Book> foundBook = bookRepository.findById(book1.getId());

        // Assert
        assertEquals(foundBook.get(), book1);
    }


    // Read All
    @Test
    public void shouldGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setIsbn("1");
        book1.setPublishDate(LocalDate.of(2022, 10, 1));
        book1.setAuthorId(this.author.getId());
        book1.setTitle("Netflix Original");
        book1.setPublisherId(this.publisher.getId());
        book1.setPrice(new BigDecimal("23.45"));

        Book book2 = new Book();
        book2.setIsbn("1");
        book2.setPublishDate(LocalDate.of(2022, 10, 1));
        book2.setAuthorId(this.author.getId());
        book2.setTitle("Netflix Original");
        book2.setPublisherId(this.publisher.getId());
        book2.setPrice(new BigDecimal("23.45"));

        // Act
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        List<Book> foundBooks = bookRepository.findAll();

        // Assert
        assertEquals(2, foundBooks.size());
        assertTrue(foundBooks.contains(book1));
        assertTrue(foundBooks.contains(book2));
    }


    // Update
    @Test
    public void shouldUpdateBook() {
        // Arrange
        Book book = new Book();
        book.setIsbn("1");
        book.setPublishDate(LocalDate.of(2022, 10, 1));
        book.setAuthorId(this.author.getId());
        book.setTitle("Netflix Original");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("23.45"));

        book = bookRepository.save(book);

        book.setIsbn("2");
        book.setPublishDate(LocalDate.of(2023, 11, 2));
        book.setAuthorId(this.author.getId());
        book.setTitle("Barbie");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("57.33"));

        book = bookRepository.save(book);

        // Act
        Optional<Book> book1 = bookRepository.findById(book.getId());

        // Assert
        assertEquals(book1.get(), book);
    }


    // Delete by ID
    @Test
    public void shouldDeleteBookById() {
        // Arrange
        Book book = new Book();
        book.setIsbn("1");
        book.setPublishDate(LocalDate.of(2022, 10, 1));
        book.setAuthorId(this.author.getId());
        book.setTitle("Netflix Original");
        book.setPublisherId(this.publisher.getId());
        book.setPrice(new BigDecimal("23.45"));

        book = bookRepository.save(book);

        // Act
        Optional<Book> foundBook = bookRepository.findById(book.getId());

        // Assert
        assertTrue(foundBook.isPresent());
        assertEquals(foundBook.get(), book);

        // Act
        bookRepository.deleteById(book.getId());

        foundBook = bookRepository.findById(book.getId());

        // Assert
        assertFalse(foundBook.isPresent());
    }


    // Search Book by Author ID
    @Test
    public void shouldGetBookByAuthorId() {
        // Arrange
        Book book1 = new Book();
        book1.setIsbn("3");
        book1.setPublishDate(LocalDate.of(2022, 10, 1));
        book1.setAuthorId(this.author.getId());
        book1.setTitle("Netflix Original");
        book1.setPublisherId(this.publisher.getId());
        book1.setPrice(new BigDecimal("23.45"));

        // Act
        book1 = bookRepository.save(book1);
        Optional<List<Book>> foundBooksList = bookRepository.findByAuthorId(book1.getAuthorId());

        // Assert
        assertEquals(foundBooksList.get().size(), 1);
        assertEquals(foundBooksList.get().get(0), book1);
    }
}

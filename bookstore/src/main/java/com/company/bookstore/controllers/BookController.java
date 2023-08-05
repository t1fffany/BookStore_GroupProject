package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepository authorRepository;

    // POST: create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // GET: read by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()) {
            return foundBook.get();
        } else {
            return null;
        }
    }

    // GET: read all
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // PUT: update
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Book customer) {
        bookRepository.save(customer);
    }

    // DELETE: delete by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        bookRepository.deleteById(id);
    }

    // GET: search book by author id
    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookByAuthorId(@PathVariable int id) {
        Optional<List<Book>> foundBooks = bookRepository.findByAuthorId(id);
        if (foundBooks.isPresent()) {
            return foundBooks.get();
        } else {
            return null;
        }
    }

}

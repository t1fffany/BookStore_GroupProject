package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;



    // Author

    // todo: check if books by the author is in return object
    @QueryMapping
    public Author findAuthorById(@Argument int id) {
        Optional<Author> returnVal = this.authorRepository.findById(id);
        return returnVal.isPresent() ? (Author)returnVal.get() : null;
    }



    // Book

    // todo: check if author and publisher of the book is in return object
    @QueryMapping
    public Book findBookById(@Argument int id) {
        Optional<Book> returnVal = this.bookRepository.findById(id);
        return returnVal.isPresent() ? (Book)returnVal.get() : null;
    }



    // Publisher

    // todo: check if books for the publisher and authors for the books is in return object
    @QueryMapping
    public Publisher findPublisherById(@Argument int id) {
        Optional<Publisher> returnVal = this.publisherRepository.findById(id);
        return returnVal.isPresent() ? (Publisher)returnVal.get() : null;
    }

    // return author by book
    @SchemaMapping
    public Author author(Book book) {
        Optional<Author> returnVal = authorRepository.findById(book.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }


    // return publisher by book
    @SchemaMapping
    public Publisher publisher(Book book) {
        Optional<Publisher> returnVal = publisherRepository.findById(book.getId());
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
}

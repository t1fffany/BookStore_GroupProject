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

    public GraphController() {
    }





    // Author

    // todo: include books by the author in return object
    @QueryMapping
    public Author findAuthorById(@Argument int id) {
        Optional<Author> returnVal = this.authorRepository.findById(id);
        return returnVal.isPresent() ? (Author)returnVal.get() : null;
    }



    // Book

    // todo: include author and publisher of the book in return object
    @QueryMapping
    public Book findBookById(@Argument int id) {
        Optional<Book> returnVal = this.bookRepository.findById(id);
        return returnVal.isPresent() ? (Book)returnVal.get() : null;
    }



    // Publisher

    // todo: include books for the publisher and authors for the books in return object
    @QueryMapping
    public Publisher findPublisherById(@Argument int id) {
        Optional<Publisher> returnVal = this.publisherRepository.findById(id);
        return returnVal.isPresent() ? (Publisher)returnVal.get() : null;
    }


}

package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    private PublisherRepository publisherRepository;

    // POST: create
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // GET: read by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable int id) {
        Optional<Publisher> foundPublisher = publisherRepository.findById(id);
        if (foundPublisher.isPresent()) {
            return foundPublisher.get();
        } else {
            return null;
        }
    }

    // GET: read all
    @GetMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // PUT: update
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Publisher publisher) {
        publisherRepository.save(publisher);
    }

    // DELETE: delete by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        publisherRepository.deleteById(id);
    }

}

package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @QueryMapping
    public List<Book> books() { return bookRepository.findAll(); }

    @QueryMapping
    public List<Author> authors() { return authorRepository.findAll(); }

    @QueryMapping
    public List<Publisher> publishers() { return publisherRepository.findAll(); }

    @QueryMapping
    public Book findBookById(@Argument int id) {
        Optional<Book> book = bookRepository.findById(id); // Use Optional

        return book.orElse(null);
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument int id) {
        Optional<Publisher> publisher = publisherRepository.findById(id); // Use Optional

        return publisher.orElse(null);
    }

    @QueryMapping
    public Author findAuthorById(@Argument int id) {
        Optional<Author> author = authorRepository.findById(id); // Use Optional

        return author.orElse(null);
    }

    @SchemaMapping
    public Publisher publisher (Book book) {
        Optional<Publisher> publisher = publisherRepository.findById(book.getPublisherId()); // Use Optional

        return publisher.orElse(null);
    }

    @SchemaMapping
    public Author author (Book book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId()); // Use Optional

        return author.orElse(null);
    }


}

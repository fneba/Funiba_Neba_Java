package com.company.bookstore.controller;


import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository repo;

    // (R) Read
    @GetMapping("/books")
    public List<Book> getBooks(){
        return repo.findAll();
    }


    // ReadById
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        Optional<Book> returnVal = repo.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    // (C) Create
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return repo.save(book);
    }

    // (U) Update
    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        repo.save(book);
    }

    // (D) Delete
    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id) {
        repo.deleteById(id);
    }

    // (Special Mapping)
    @GetMapping("/books/books/{id}")
    public List<Book> getBookByAuthorId(@PathVariable int id) {
        return repo.findByAuthorId(id); }

}

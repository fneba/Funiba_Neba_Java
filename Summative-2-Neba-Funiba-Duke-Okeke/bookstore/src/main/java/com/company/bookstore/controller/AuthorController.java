package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository repo;

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return serviceLayer.findAllAuthors();
    }


    // must fix this, might be optional instead of list (look at service layer)
//    @GetMapping("/authors/{id}")
//    public Author getAuthorById(@PathVariable int id) {
//        List<Author> author = repo.findById(id);
//
//        return author;
//    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return serviceLayer.saveAuthor(author);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author) {
        serviceLayer.updateAuthor(author);
    }

    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        serviceLayer.removeAuthor(id);
    }


}

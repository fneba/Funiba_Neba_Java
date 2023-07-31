package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/publishers")
    public List<Publisher> getPublishers(){
        return serviceLayer.findAllPublishers();
    }


    // must fix this, might be optional instead of list (look at service layer)
//    @GetMapping("/publishers/{id}")
//    public Author getPublisherById(@PathVariable int id) {
//        List<Publisher> publisher = repo.findById(id);
//
//        return publisher;
//    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return serviceLayer.savePublisher(publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher) {
        serviceLayer.updatePublisher(publisher);
    }

    @DeleteMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        serviceLayer.removePublisher(id);
    }

}

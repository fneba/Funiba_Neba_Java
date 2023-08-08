package com.company.bookstore.repository;


import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PublisherRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldGetAllPublishers() {

        Publisher publisher1 = new Publisher();
        publisher1.setCity("New York");
        publisher1.setEmail("james@gmail.com");
        publisher1.setPhone("111-111-1111");
        publisher1.setName("McGrawHill");
        publisher1.setPostalCode("11111");
        publisher1.setState("NY");
        publisher1.setStreet("Publisher lane");
        publisher1 = publisherRepository.save(publisher1);


        Publisher publisher2 = new Publisher();
        publisher2.setCity("New York");
        publisher2.setEmail("bond@gmail.com");
        publisher2.setPhone("111-111-1111");
        publisher2.setName("McGrawHill");
        publisher2.setPostalCode("11112");
        publisher2.setState("NY");
        publisher2.setStreet("Publisher lane");
        publisher2 = publisherRepository.save(publisher2);


        // Act
        List<Publisher> publisherList = publisherRepository.findAll();

        // Assert
        assertEquals(2, publisherList.size());
        assertTrue(publisherList.contains(publisher1));
        assertTrue(publisherList.contains(publisher2));

    }

    @Test
    public void shouldGetPublisherById() {

        Publisher publisher1 = new Publisher();
        publisher1.setCity("New York");
        publisher1.setEmail("james@gmail.com");
        publisher1.setPhone("111-111-1111");
        publisher1.setName("McGrawHill");
        publisher1.setPostalCode("11111");
        publisher1.setState("NY");
        publisher1.setStreet("Publisher lane");
        publisher1 = publisherRepository.save(publisher1);

        Optional<Publisher> foundPublisherOptional = publisherRepository.findById(publisher1.getId());

        // Assert
        assertTrue(foundPublisherOptional.isPresent());
        assertEquals(publisher1, foundPublisherOptional.get());

    }

    @Test
    public void shouldAddPublisher() {

        Publisher publisher1 = new Publisher();
        publisher1.setCity("New York");
        publisher1.setEmail("james@gmail.com");
        publisher1.setPhone("111-111-1111");
        publisher1.setName("McGrawHill");
        publisher1.setPostalCode("11111");
        publisher1.setState("NY");
        publisher1.setStreet("Publisher lane");
        publisher1 = publisherRepository.save(publisher1);

        // Act
        Publisher savedPublisher = publisherRepository.save(publisher1);

        // Assert
        assertNotNull(savedPublisher.getId());
        assertEquals(publisher1.getName(), savedPublisher.getName());


    }

    @Test
    public void shouldUpdatePublisher() {

        Publisher publisher1 = new Publisher();
        publisher1.setCity("New York");
        publisher1.setEmail("james@gmail.com");
        publisher1.setPhone("111-111-1111");
        publisher1.setName("McGrawHill");
        publisher1.setPostalCode("11111");
        publisher1.setState("NY");
        publisher1.setStreet("Publisher lane");
        publisher1 = publisherRepository.save(publisher1);

        // Act
        publisher1.setEmail("updated_publisher@gmail.com");
        Publisher updatedPublisher = publisherRepository.save(publisher1);

        // Assert
        assertEquals(publisher1.getId(), updatedPublisher.getId());
        assertEquals("updated_publisher@gmail.com", updatedPublisher.getEmail());

    }

    @Test
    public void shouldDeletePublisher() {

        Publisher publisher1 = new Publisher();
        publisher1.setCity("New York");
        publisher1.setEmail("james@gmail.com");
        publisher1.setPhone("111-111-1111");
        publisher1.setName("McGrawHill");
        publisher1.setPostalCode("11111");
        publisher1.setState("NY");
        publisher1.setStreet("Publisher lane");
        publisher1 = publisherRepository.save(publisher1);

        // Act
        publisherRepository.deleteById(publisher1.getId());

        // Assert
        assertFalse(publisherRepository.existsById(publisher1.getId()));

    }

}
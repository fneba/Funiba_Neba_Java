package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorRepositoryTest {

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
    public void shouldGetAllAuthors() {
        // Arrange
        Author author1 = new Author();
        author1.setCity("New York");
        author1.setEmail("james@gmail.com");
        author1.setPhone("111-111-1111");
        author1.setFirstName("James");
        author1.setLastName("Doe");
        author1.setPostalCode("11111");
        author1.setState("NY");
        author1.setStreet("Author lane");
        author1 = authorRepository.save(author1);

        Author author2 = new Author();
        author2.setCity("New York");
        author2.setEmail("paul@gmail.com");
        author2.setPhone("111-111-1111");
        author2.setFirstName("Paul");
        author2.setLastName("Doe");
        author2.setPostalCode("11111");
        author2.setState("NY");
        author2.setStreet("Author lane");
        author2 = authorRepository.save(author2);


        // Act
        List<Author> authorList = authorRepository.findAll();

        // Assert
        assertEquals(2, authorList.size());
        assertTrue(authorList.contains(author1));
        assertTrue(authorList.contains(author2));
    }
    //
    @Test
    public void shouldGetAuthorById() {
        // Arrange
        Author author1 = new Author();
        author1.setCity("New York");
        author1.setEmail("james@gmail.com");
        author1.setPhone("111-111-1111");
        author1.setFirstName("James");
        author1.setLastName("Doe");
        author1.setPostalCode("11111");
        author1.setState("NY");
        author1.setStreet("Author lane");
        author1 = authorRepository.save(author1);
        // Act
        Optional<Author> foundAuthorOptional = authorRepository.findById(author1.getId());

        // Assert
        assertTrue(foundAuthorOptional.isPresent());
        assertEquals(author1, foundAuthorOptional.get());
    }

    @Test
    public void shouldAddAuthor() {
        // Arrange
        Author author1 = new Author();
        author1.setCity("New York");
        author1.setEmail("james@gmail.com");
        author1.setPhone("111-111-1111");
        author1.setFirstName("James");
        author1.setLastName("Doe");
        author1.setPostalCode("11111");
        author1.setState("NY");
        author1.setStreet("Author lane");
        author1 = authorRepository.save(author1);
        // Act
        Author savedAuthor = authorRepository.save(author1);

        // Assert
        assertNotNull(savedAuthor.getId());
        assertEquals(author1.getFirstName(), savedAuthor.getFirstName());
        assertEquals(author1.getLastName(), savedAuthor.getLastName());
    }
    //
    @Test
    public void shouldUpdateAuthor() {
        // Arrange
        Author author1 = new Author();
        author1.setCity("New York");
        author1.setEmail("james@gmail.com");
        author1.setPhone("111-111-1111");
        author1.setFirstName("James");
        author1.setLastName("Doe");
        author1.setPostalCode("11111");
        author1.setState("NY");
        author1.setStreet("Author lane");
        author1 = authorRepository.save(author1);
        // Act
        author1.setEmail("updated_author@gmail.com");
        Author updatedAuthor = authorRepository.save(author1);

        // Assert
        assertEquals(author1.getId(), updatedAuthor.getId());
        assertEquals("updated_author@gmail.com", updatedAuthor.getEmail());
    }
    //
    @Test
    public void shouldDeleteAuthor() {
        // Arrange
        Author author1 = new Author();
        author1.setCity("New York");
        author1.setEmail("james@gmail.com");
        author1.setPhone("111-111-1111");
        author1.setFirstName("James");
        author1.setLastName("Doe");
        author1.setPostalCode("11111");
        author1.setState("NY");
        author1.setStreet("Author lane");
        author1 = authorRepository.save(author1);
        // Act
        authorRepository.deleteById(author1.getId());

        // Assert
        assertFalse(authorRepository.existsById(author1.getId()));
    }

}
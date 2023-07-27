package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {




    @Autowired
    CustomerRepository customerRepo;

    @BeforeEach
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    // It Works
    @Test
    public void addCustomer() {

        //Arrange... (1 customer formed to be added to repo)
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);


        // put all found customers in repo into a list.
        List<Customer> customerList = customerRepo.findAll();

        //Assert... (One customer was added so size of list should be 1)
        assertEquals(1,customerList.size());

    }

    @Test
    public void shouldGetCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");


        customer = customerRepo.save(customer);

        Customer cust = customerRepo.findById(customer.getId()).get();

        Assertions.assertThat(cust.getId()).isEqualTo(customer.getId());

    }

    @Test
    public void shouldUpdateCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");


        customer = customerRepo.save(customer);

        customer.setEmail("newEmail@gmail.com");

        Customer updatedCustomer = customerRepo.save(customer);

        assertEquals(updatedCustomer.getEmail(),"newEmail@gmail.com");



    }

    @Test
    public void shouldDeleteCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);

        customerRepo.deleteById(customer.getId());

        List<Customer> customerList = customerRepo.findAll();

        //Assert...
        assertEquals(0,customerList.size());

    }

    @Test
    public void shouldFilterCustomersByState() {

        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress1("1234 Harper Ln");
        customer.setCity("College Park");
        customer.setAddress2("Apt 7B");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setPostalCode("31559");
        customer.setState("Maryland");
        customer.setCountry("United States");

        customer = customerRepo.save(customer);

        Customer customer2 = new Customer();
        customer2.setFirstName("Joe");
        customer2.setLastName("Smith");
        customer2.setPhone("111-222-3456");
        customer2.setCompany("BigCo");
        customer2.setAddress1("1234 Harper Ln");
        customer2.setCity("College Park");
        customer2.setAddress2("Apt 7B");
        customer2.setEmail("JoeSmith@gmail.com");
        customer2.setPostalCode("31559");
        customer2.setState("Maryland");
        customer2.setCountry("United States");

        customer2 = customerRepo.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Joe");
        customer3.setLastName("Smith");
        customer3.setPhone("111-222-3456");
        customer3.setCompany("BigCo");
        customer3.setAddress1("1234 Harper Ln");
        customer3.setCity("College Park");
        customer3.setAddress2("Apt 7B");
        customer3.setEmail("JoeSmith@gmail.com");
        customer3.setPostalCode("31559");
        customer3.setState("Pennsylania");
        customer3.setCountry("United States");

        customer3 = customerRepo.save(customer3);

        List<Customer> customerList = customerRepo.findAllByState("Maryland");

        //Assert...
        assertEquals(2,customerList.size());

    }

}
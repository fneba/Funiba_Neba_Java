package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;



    // Create i.e. "Create a new customer record"
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {

        return repo.save(customer);
    }

    // Read (by id) i.e. "Find a customer record by id"
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id){
        Optional<Customer> returnVal = repo.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    // Update i.e. "Update an existing customer record"
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer){

        repo.save(customer);
    }

    // Delete i.e. "Delete and existing customer record"
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){

        repo.deleteById(id);
    }

    // Custom: filter by state i.e. "Find customer records by state"
    @GetMapping("/customers/state/{state}")
    public List<Customer> filterCustomersByState(@PathVariable String state){
        return repo.findAllByState(state);
    }
}

package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.AccountException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;


class CustomerTest {

    Customer cust;
    AccountRecord rec, recc;
    List<AccountRecord> charges;
    // We will do three tests for getBalance. Each test will have two charges
    // to sum. (20, 40) , (-10, -20) , (-60, 40)
    // will only be doing one toString() test though


    @BeforeEach
    void setUp() {
        cust = new Customer();
        rec = new AccountRecord();
        recc = new AccountRecord();
    }

    @Test
    public void shouldSumCharges(){
        String Name = "testCust"; // Name of customer
        Integer id = 1; // grabs the id of the strings in customerData
        Integer charge1 = 20; // amount of new charge
        Integer charge2 = 40;
        cust.setId(id);
        cust.setName(Name);
        rec.setCharge(charge1);
        recc.setCharge(charge2);
        cust.getCharges().add(rec);
        cust.getCharges().add(recc);

        assertEquals(60, cust.getBalance());
    }

    @Test
    public void shouldSumCharges2(){
        String Name = "testCust"; // Name of customer
        Integer id = 1; // grabs the id of the strings in customerData
        Integer charge1 = -10; // amount of new charge
        Integer charge2 = -20;
        cust.setId(id);
        cust.setName(Name);
        rec.setCharge(charge1);
        recc.setCharge(charge2);
        cust.getCharges().add(rec);
        cust.getCharges().add(recc);

        assertEquals(-30, cust.getBalance());
    }

    @Test
    public void shouldSumCharges3(){
        String Name = "testCust"; // Name of customer
        Integer id = 1; // grabs the id of the strings in customerData
        Integer charge1 = -60; // amount of new charge
        Integer charge2 = 40;
        cust.setId(id);
        cust.setName(Name);
        rec.setCharge(charge1);
        recc.setCharge(charge2);
        cust.getCharges().add(rec);
        cust.getCharges().add(recc);

        assertEquals(-20, cust.getBalance());
    }

    @Test
    public void shouldPrintCustomerInfo(){
        String Name = "testCust"; // Name of customer
        Integer id = 1; // grabs the id of the strings in customerData
        Integer charge1 = -60; // amount of new charge
        Integer charge2 = 40;
        cust.setId(id);
        cust.setName(Name);
        rec.setCharge(charge1);
        recc.setCharge(charge2);
        cust.getCharges().add(rec);
        cust.getCharges().add(recc);

        assertEquals("Customer ID: 1 | Name: testCust | Balance: -20" + "\n", cust.toString());
    }

}
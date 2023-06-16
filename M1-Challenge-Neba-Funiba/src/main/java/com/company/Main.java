package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        //Update this

        List<Customer> customers = new ArrayList<>(); // create new ArrayList for consolidated customers
        Map<Integer, Customer> customerHash = new HashMap<>(); // create hash map to maintain uniqueness of customers

        for (String[] cusD:customerData){

            Customer customer = new Customer(); // customer instantiation
            AccountRecord record = new AccountRecord(); // account record instantiation
            String Name = cusD[1]; // Name of customer
            Integer id = Integer.parseInt(cusD[0]); // grabs the id of the strings in customerData
            Integer charge = Integer.parseInt(cusD[2]); // amount of new charge
            String date = cusD[3]; // date of new charge

            // next 5 lines sets up grabbed data for customer

            customer.setId(id);
            customer.setName(Name);
            record.setCharge(charge);
            record.setChargeDate(date);
            customer.getCharges().add(record);

            // conditionals so that if an id doesn't exist in hashmap, add a customer with the charges. If id does exist
            // only add charges to customer with that id
            if (customerHash.containsKey(id)){
                customerHash.get(id).getCharges().add(record);
            } else {
                customerHash.put(customer.getId(), customer);
            }

        }

        // add all the customers, with their charges, to the customers arraylist
        customers.addAll(customerHash.values());

        // filtered customers arraylist -> gives customers with positive balance
        List<Customer> positiveCustomers = customers.stream()
                .filter(c -> c.getBalance() > 0).collect(Collectors.toList());

        // filtered customers arraylist -> gives customers with negative balance
        List<Customer> negativeCustomers = customers.stream()
                .filter(c -> c.getBalance() < 0).collect(Collectors.toList());


        System.out.println("Positive accounts:" + "\n" + positiveCustomers.toString().replace("[","").replace("]","").replace(", ",""));
        System.out.println("Negative accounts:" + "\n" + negativeCustomers.toString().replace("[","").replace("]",""));
    }
}

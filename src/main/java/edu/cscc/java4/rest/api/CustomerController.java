package edu.cscc.java4.rest.api;

import edu.cscc.java4.rest.data.CustomerRepository;
import edu.cscc.java4.rest.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/api/customers")
    public Map<String,Iterable<Customer>> getAllCustomers(){
        Map<String,Iterable<Customer>> customers = new HashMap<>();
        customers.put("customers",customerRepository.findAll());
        return customers;

    }

    @GetMapping("/api/customers/{id}")
    public Optional<Customer> getAllCustomersById(@PathVariable Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        if(!customer.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"entity not found");

        }
        return customer;

    }
    @DeleteMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomersById(@PathVariable Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        if(!customer.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"entity not found");
        }

        customerRepository.delete(customer.get());

    }

    @PutMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomersById(@PathVariable Long id, @RequestBody Customer customer){
        boolean customerExists = customerRepository.existsById(id);

        if(!customerExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"entity not found");
        }
        customerRepository.save(customer);

    }

    @PostMapping("/api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody Customer customer) {
        Customer newCustomer = customerRepository.save(customer);

        UriComponentsBuilder.newInstance().scheme("http").host("localhost");

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").path("/api/customers/{id}").buildAndExpand(newCustomer.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();

    }

}

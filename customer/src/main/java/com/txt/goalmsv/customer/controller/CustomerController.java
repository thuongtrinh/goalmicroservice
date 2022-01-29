package com.txt.goalmsv.customer.controller;

import com.txt.goalmsv.customer.request.CustomerRegistrationRequest;
import com.txt.goalmsv.customer.response.CustomerResponse;
import com.txt.goalmsv.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") String id) {
        log.info("get customer by id {}", id);
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerResponse> getCustomer() {
        log.info("get all customer");
        return customerService.getAllCustomer();
    }
}

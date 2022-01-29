package com.txt.goalmsv.customer.service;

import com.txt.goalmsv.clients.fraud.FraudCheckResponse;
import com.txt.goalmsv.clients.fraud.FraudClient;
import com.txt.goalmsv.customer.entity.Customer;
import com.txt.goalmsv.customer.repository.CustomerRepository;
import com.txt.goalmsv.customer.request.CustomerRegistrationRequest;
import com.txt.goalmsv.customer.response.CustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }

    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(Integer.parseInt(id)).get();
        CustomerResponse response = new CustomerResponse();
        if(customer != null && customer.getId() != null) {
            System.out.println(customer);
            BeanUtils.copyProperties(customer, response);
        }
        return response;
    }

    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> responses = new ArrayList<>();
        if(customers != null && customers.size() > 0){
            for (Customer customer : customers) {
                CustomerResponse response = new CustomerResponse();
                BeanUtils.copyProperties(customer, response);
                responses.add(response);
            }
        }
        return responses;
    }
}

package com.txt.goalmsv.customer.repository;

import com.txt.goalmsv.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

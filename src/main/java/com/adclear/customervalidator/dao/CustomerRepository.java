package com.adclear.customervalidator.dao;

import com.adclear.customervalidator.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

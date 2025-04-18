package com.example.mvcobjectmapper.repository;

import com.example.mvcobjectmapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

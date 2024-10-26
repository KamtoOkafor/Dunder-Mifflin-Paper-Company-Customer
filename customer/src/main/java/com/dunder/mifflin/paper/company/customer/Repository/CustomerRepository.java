package com.dunder.mifflin.paper.company.customer.Repository;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
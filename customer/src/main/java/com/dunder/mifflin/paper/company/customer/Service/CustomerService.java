package com.dunder.mifflin.paper.company.customer.Service;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
	private static final Logger logger = LoggerFactory.getLogger((Customer.class));

	@Autowired
	private final CustomerRepository customerRepository;

	// save and return Customer entity stored in the DB
	public Customer createCustomer(Customer customer) {
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			logger.error("error creating customer: {}", customer);
			throw e;
		}
	}

	// Get all customers currently in the DB
	public List<Customer> getAllCustomers() {
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			logger.error("error getting all customers");
			throw e;
		}
	}

	// get customer by ID, returns optional, in the case where customer doesn't
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElse(null);
	}

	public void deleteCustomer(Long id) {
		try {
			customerRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("error deleting customer by id: {}", id);
			throw e;
		}
	}
}

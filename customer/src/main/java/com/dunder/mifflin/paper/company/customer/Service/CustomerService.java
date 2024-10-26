package com.dunder.mifflin.paper.company.customer.Service;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing {@link Customer} entities.
 * <p>
 * This service provides methods for creating, retrieving, and deleting {@link Customer} records
 * from the database. It relies on {@link CustomerRepository} for database operations and
 * logs significant events and errors.
 * </p>
 *
 * <p>Primary methods:</p>
 * <ul>
 *     <li>{@link #createCustomer(Customer)}: Saves a new customer to the database.</li>
 *     <li>{@link #getAllCustomers()}: Retrieves all customers currently stored in the database.</li>
 *     <li>{@link #getCustomerById(Long)}: Retrieves a customer by their unique ID.</li>
 *     <li>{@link #deleteCustomer(Long)}: Deletes a customer by their unique ID.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * Customer newCustomer = new Customer("John", "Doe", "john.doe@example.com");
 * Customer savedCustomer = customerService.createCustomer(newCustomer);
 * List<Customer> allCustomers = customerService.getAllCustomers();
 * }
 * </pre>
 *
 * @see Customer
 * @see CustomerRepository
 */
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

	// get customer by ID, returns the customer or null
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElse(null);
	}

	// delete customer by id
	public void deleteCustomer(Long id) {
		try {
			customerRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("error deleting customer by id: {}", id);
			throw e;
		}
	}
}

package com.dunder.mifflin.paper.company.customer.Repository;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Service.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for performing CRUD operations on {@link Customer} entities.
 *
 * <p>This interface extends {@link JpaRepository}, providing a range of database
 * interaction methods for {@link Customer} entities. It uses Spring Data JPA's
 * default methods for operations like saving, finding, updating, and deleting
 * {@link Customer} records.</p>
 *
 * <p>By extending {@link JpaRepository}, this repository inherits a wide array
 * of CRUD operations without the need for boilerplate code, allowing the
 * underlying persistence mechanism to be abstracted away.</p>
 *
 * <p>Primary Key Type: {@link Long}</p>
 *
 * Example usage:
 * <pre>
 * {@code
 * Customer customer = new Customer("John", "Doe", "john.doe@example.com");
 * customerRepository.save(customer); // Save a new customer
 * Optional<Customer> result = customerRepository.findById(1L); // Retrieve a customer by ID
 * }
 * </pre>
 *
 * @see JpaRepository
 * @see Customer
 * @see CustomerService
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> { }
package repo;

import com.dunder.mifflin.paper.company.customer.CustomerApplication;
import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utill.TestUtil.newCustomer;

@DataJpaTest // Loads the context for JPA testing
@ContextConfiguration(classes = {CustomerApplication.class})
class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger logger = LoggerFactory.getLogger((CustomerRepositoryTest.class));

	@Test
	public void testSaveCustomer() {
		Customer customer = newCustomer("Alice", "Smith", "alice@example.com", 1L);
		Customer savedCustomer = customerRepository.save(customer);

		assertNotNull(savedCustomer.getId());
		assertEquals("Alice", savedCustomer.getFirstName());
		assertEquals("Smith", savedCustomer.getLastName());
		assertEquals("alice@example.com", savedCustomer.getEmail());
	}

	@Test
	public void testFindAllCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();
		logger.info(allCustomers.toString());

		assertNotNull(allCustomers);
		assertEquals((long) allCustomers.size(), 3);
	}

	@Test
	public void testFindCustomerById() {
		Customer customer = newCustomer("Bob", "Smith", "alice@example.com", 1L);
		customer = customerRepository.save(customer);

		Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
		assertTrue(foundCustomer.isPresent());
		assertEquals("Bob", foundCustomer.get().getFirstName());
	}

	@Test
	public void testDeleteCustomer() {
		Customer customer = newCustomer("Alice", "Smith", "alice@example.com", 1L);
		customer = customerRepository.save(customer);

		customerRepository.deleteById(customer.getId());
		Optional<Customer> deletedCustomer = customerRepository.findById(customer.getId());
		assertFalse(deletedCustomer.isPresent());
	}
}

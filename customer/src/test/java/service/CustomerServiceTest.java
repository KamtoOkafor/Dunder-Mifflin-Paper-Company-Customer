package service;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Repository.CustomerRepository;
import com.dunder.mifflin.paper.company.customer.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utill.TestUtil.newCustomer;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerService customerService;

	@Test
	public void testCreateCustomer() {
		Customer customer = newCustomer("Alice", "Smith", "alice@example.com", 1L);
		when(customerRepository.save(any())).thenReturn(customer);

		Customer createdCustomer = customerService.createCustomer(customer);
		assertNotNull(createdCustomer);
		assertEquals("Alice", createdCustomer.getFirstName());
		assertEquals("Smith", createdCustomer.getLastName());
	}

	@Test
	public void testGetAllCustomers() {
		Customer customer1 = newCustomer("John", "Doe", "john@example.com", 1L);
		Customer customer2 = newCustomer("Jane", "Doe", "jane@example.com", 2L);
		List<Customer> customers = Arrays.asList(customer1, customer2);

		when(customerService.getAllCustomers()).thenReturn(customers);

		List<Customer> response = customerService.getAllCustomers();
		assertEquals(2, response.size());
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = newCustomer("John", "Doe", "john@example.com", 1L);
		when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

		Customer fetechedCustomer = customerService.getCustomerById(1L);
		assertEquals("John", fetechedCustomer.getFirstName());
	}

	@Test
	public void testDeleteCustomer() {
		long id = 1L;

		customerService.deleteCustomer(id);
		verify(customerRepository, times(1)).deleteById(id);
	}
}
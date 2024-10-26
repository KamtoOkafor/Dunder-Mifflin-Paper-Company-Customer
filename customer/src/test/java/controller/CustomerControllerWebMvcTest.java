package controller;

import com.dunder.mifflin.paper.company.customer.Controller.CustomerController;
import com.dunder.mifflin.paper.company.customer.CustomerApplication;
import com.dunder.mifflin.paper.company.customer.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(classes= CustomerApplication.class)
@Transactional // Ensures that each test runs in a transaction that is rolled back after the test
public class CustomerControllerWebMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testCreateCustomer() throws Exception {
		String json = "{\"firstName\": \"David\", \"lastName\": \"Williams\", \"email\": \"david@example.com\", \"state\": \"TX\", \"phoneNumber\": \"555-987-6543\"}";

		mockMvc.perform(post("/v1/api/customers")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("David"))
				.andExpect(jsonPath("$.lastName").value("Williams"))
				.andExpect(jsonPath("$.email").value("david@example.com"));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		// Customer with id=1 is 'Alice Smith' from the data.sql file
		mockMvc.perform(get("/v1/api/customers/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.firstName").value("Alice"))
				.andExpect(jsonPath("$.lastName").value("Smith"))
				.andExpect(jsonPath("$.email").value("alice@example.com"));
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		// Expecting the 3 customers from the data.sql
		mockMvc.perform(get("/v1/api/customers")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(3)) // 3 customers inserted
				.andExpect(jsonPath("$[0].firstName").value("Alice"))
				.andExpect(jsonPath("$[1].firstName").value("Bob"))
				.andExpect(jsonPath("$[2].firstName").value("Charlie"));
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		// Deleting the customer with id=1 (Alice Smith)
		mockMvc.perform(delete("/v1/api/customers/1"))
				.andExpect(status().isNoContent());

		// Verify the customer has been deleted
		mockMvc.perform(get("/v1/api/customers/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}

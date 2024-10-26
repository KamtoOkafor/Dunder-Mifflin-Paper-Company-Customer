package com.dunder.mifflin.paper.company.customer.Controller;

import com.dunder.mifflin.paper.company.customer.Model.Customer;
import com.dunder.mifflin.paper.company.customer.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a customer by ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Customer found"),
			@ApiResponse(responseCode = "404", description = "Customer not found")
	})
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		return customer == null ?
				new ResponseEntity<>(HttpStatus.NOT_FOUND) :
				new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Create a new customer")
	@ApiResponse(responseCode = "201", description = "Customer created successfully")
	public ResponseEntity<Customer> createCustomer(
			@RequestBody Customer customer
	) {
		return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "204", description = "Customer deleted successfully")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


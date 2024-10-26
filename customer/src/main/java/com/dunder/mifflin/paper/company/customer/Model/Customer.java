package com.dunder.mifflin.paper.company.customer.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


/**
 * Represents a customer in the system with fields for personal information.
 *
 * <p>This entity is mapped to a database table using JPA, and its primary key is
 * automatically generated. Each customer has a unique identifier along with fields
 * for first name, last name, email, state, and phone number.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * Customer customer = new Customer();
 * customer.setFirstName("John");
 * customer.setLastName("Doe");
 * customer.setEmail("john.doe@example.com");
 * }
 * </pre>
 *
 * @see javax.persistence.Entity
 * @see javax.persistence.GeneratedValue
 */
@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	@Column(nullable = true)
	private String state;
	@Column(nullable = true)
	private String phoneNumber;
}

package com.dunder.mifflin.paper.company.customer.Model;

import jakarta.persistence.*;
import lombok.Data;

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

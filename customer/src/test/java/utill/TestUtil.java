package utill;

import com.dunder.mifflin.paper.company.customer.Model.Customer;

public class TestUtil {

	public static Customer newCustomer(String firstName, String lastName, String email, Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);

		return customer;
	}
}

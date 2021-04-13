package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    // customers
    List<Customer> findAllCustomers();

    // customers/orders


    // customers/:id
    Customer findCustomerById(long id);

    // customers/namelike/:likename
    List<Customer> findByCustnameLike(String subname);

    // customers/orders/count
}

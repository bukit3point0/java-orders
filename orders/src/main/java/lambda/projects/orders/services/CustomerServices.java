package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.views.CustomerOrders;
import lambda.projects.orders.views.OrderCounts;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    void delete(long id);

    // customers
    List<Customer> findAllCustomers();

    // customers/orders


    // customers/:id
    Customer findCustomerById(long id);

    // customers/namelike/:likename
    List<Customer> findByCustnameLike(String subname);

    // customers/orders/count
    List <OrderCounts> getCountOrders();

    List<CustomerOrders> getOrdNum();
}

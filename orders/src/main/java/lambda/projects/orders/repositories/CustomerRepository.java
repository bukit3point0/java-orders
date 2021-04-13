package lambda.projects.orders.repositories;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByCustnameContainingIgnoringCase(String likecustname);

    @Query(value = "SELECT c.custname custname, count(ordnum) countorders " +
            "FROM customers c LEFT JOIN orders o " +
            "ON c.custcode = o.custcode " +
            "GROUP BY c.custname " +
            "ORDER BY countorders DESC",
            nativeQuery = true)
    List<OrderCounts> findCountOrders();
}

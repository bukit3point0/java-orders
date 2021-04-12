package lambda.projects.orders.services;

import lambda.projects.orders.models.Order;
import lambda.projects.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "orderService")
public class OrderServicesImplementation implements OrderServices{
    @Autowired
    private OrderRepository orderrepos;

    @Transactional
    @Override
    public Order save(Order order) {
        return orderrepos.save(order);
    }
}

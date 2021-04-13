package lambda.projects.orders.services;

import lambda.projects.orders.models.Order;
import lambda.projects.orders.repositories.OrderRepository;
import lambda.projects.orders.views.CustomerOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @Override
    public Order findOrderById(long ordnum) {
        return orderrepos
                .findById(ordnum)
                .orElseThrow(() -> new EntityNotFoundException("Order " + ordnum + " not found"));
    }

}

package lambda.projects.orders.services;

import lambda.projects.orders.models.Order;
import lambda.projects.orders.views.CustomerOrders;
import lambda.projects.orders.views.OrderCounts;

import java.util.List;

public interface OrderServices {
    Order save(Order order);

    // orders/order/:id
    Order findOrderById(long id);
}

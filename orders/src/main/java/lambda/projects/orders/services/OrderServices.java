package lambda.projects.orders.services;

import lambda.projects.orders.models.Order;

public interface OrderServices {
    Order save(Order order);

    // orders/order/:id
    Order findOrderById(long id);
}

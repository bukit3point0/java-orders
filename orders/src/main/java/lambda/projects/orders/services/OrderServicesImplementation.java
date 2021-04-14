package lambda.projects.orders.services;

import lambda.projects.orders.models.Order;
import lambda.projects.orders.models.Payment;
import lambda.projects.orders.repositories.OrderRepository;
import lambda.projects.orders.repositories.PaymentRepository;
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

    @Autowired
    private PaymentRepository payrepos;

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if (order.getOrdnum() != 0) {
            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " not found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        newOrder.getPayments().clear();
        for (Payment p : order.getPayments()) {
            Payment newPayment = payrepos.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found"));
            newOrder.getPayments().add(newPayment);
        }

        return orderrepos.save(order);
    }

    @Override
    public Order findOrderById(long ordnum) {
        return orderrepos
                .findById(ordnum)
                .orElseThrow(() -> new EntityNotFoundException("Order " + ordnum + " not found"));
    }

    @Override
    public void delete(long id) {
        if (orderrepos.findById(id).isPresent()) {
            orderrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order " + id + " not found");
        }
    }

    @Override
    public Order update(Order order, long id) {
        Order currentOrder = orderrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " not found"));

        if (order.hasadvanceamount) {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if (order.hasordamount) {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        if(order.getOrderdescription() != null) {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

        if(order.getCustomer() != null) {
            currentOrder.setCustomer(order.getCustomer());
        }

        if (order.getPayments().size() > 0) {
            currentOrder.getPayments().clear();
            for (Payment p : order.getPayments()) {
                Payment newPayment = payrepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " not found"));
                currentOrder.getPayments().add(newPayment);
            }
        }

        return orderrepos.save(order);
    }

}

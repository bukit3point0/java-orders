package lambda.projects.orders.services;

import lambda.projects.orders.models.Payment;

public interface PaymentServices {
    Payment save(Payment payment);
}

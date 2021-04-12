package lambda.projects.orders.services;

import lambda.projects.orders.models.Payment;
import lambda.projects.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "paymentService")
public class PaymentServicesImplementation implements PaymentServices{
    @Autowired
    private PaymentRepository payrepos;

    @Transactional
    @Override
    public Payment save(Payment payment) {
        return payrepos.save(payment);
    }
}

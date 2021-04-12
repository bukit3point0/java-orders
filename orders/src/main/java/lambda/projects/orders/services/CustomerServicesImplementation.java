package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImplementation implements CustomerServices{
    @Autowired
    private CustomerRepository custrepos;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return custrepos.save(customer);
    }
}

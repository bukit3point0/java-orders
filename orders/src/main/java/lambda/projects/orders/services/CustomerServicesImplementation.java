package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer findCustomerById(long id) {
        return custrepos
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " not found"));
    }

    @Override
    public List<Customer> findByCustnameLike(String subcustname) {
        List<Customer> returnList = custrepos.findByCustnameContainingIgnoringCase(subcustname);
        return returnList;
    }

}

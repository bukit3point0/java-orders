package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.repositories.CustomerRepository;
import lambda.projects.orders.views.CustomerOrders;
import lambda.projects.orders.views.OrderCounts;
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
    public Customer findCustomerById(long custcode) {
        return custrepos
                .findById(custcode)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + custcode + " not found"));
    }

    @Override
    public List<Customer> findByCustnameLike(String subcustname) {
        List<Customer> returnList = custrepos.findByCustnameContainingIgnoringCase(subcustname);
        return returnList;
    }

    @Override
    public List<OrderCounts> getCountOrders() {
        List<OrderCounts> returnList = custrepos.findCountOrders();
        return returnList;
    }

    @Override
    public List<CustomerOrders> getOrdNum() {
        List<CustomerOrders> returnList = custrepos.findCustomerOrders();
        return returnList;
    }
}

package lambda.projects.orders.services;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.models.Order;
import lambda.projects.orders.models.Payment;
import lambda.projects.orders.repositories.AgentRepository;
import lambda.projects.orders.repositories.CustomerRepository;
import lambda.projects.orders.repositories.OrderRepository;
import lambda.projects.orders.repositories.PaymentRepository;
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

    @Autowired
    private OrderRepository orderrepos;

    @Autowired
    private PaymentRepository payrepos;

    @Autowired
    private AgentRepository agentrepos;

    @Transactional
    @Override
    // Customer
    // orders
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();

        // PUT request section
        if(customer.getCustcode() != 0) {
            findCustomerById(customer.getCustcode());

            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = new Order();

            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setCustomer(newCustomer);

            newOrder.getPayments().clear();
            for (Payment p : o.getPayments()) {
                Payment newPayment = payrepos.findById(p.getPaymentid())
                        .orElseThrow(() -> new EntityNotFoundException("Payment type " + p.getPaymentid() + " not found"));
                newOrder.getPayments().add(newPayment);
            }

            newCustomer.getOrders().add(newOrder);
        }

        // currently wrong: agent not setting, payments not setting

        return custrepos.save(customer);
    }

    @Override
    public void delete(long id) {
        if (custrepos.findById(id).isPresent()) {
            custrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " not found");
        }
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

    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " not found"));

        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.hasopeningamt) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasreceiveamt) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.haspaymentamt) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasoutstandingamt) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null) {
            currentCustomer.setAgent(customer.getAgent());
        }

        if (customer.getOrders().size() > 0) {

            currentCustomer.getOrders().clear();
            for (Order o : customer.getOrders()) {
                Order newOrder = new Order();

                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setCustomer(currentCustomer);

                if(newOrder.getPayments().size() > 0) {
                    newOrder.getPayments().clear();
                    for (Payment p : o.getPayments()) {
                        Payment newPayment = payrepos.findById(p.getPaymentid())
                                .orElseThrow(() -> new EntityNotFoundException("Payment type " + p.getPaymentid() + " not found"));

                        newOrder.getPayments().add(newPayment);
                    }
                }

                currentCustomer.getOrders().add(newOrder);
            }
        }

        return custrepos.save(currentCustomer);
    }
}

package lambda.projects.orders.controllers;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.services.CustomerServices;
import lambda.projects.orders.views.CustomerOrders;
import lambda.projects.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerServices.findAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    // bad misunderstanding, wrong code
//    // http://localhost:2019/customers/orders
//    @GetMapping(value = "/orders", produces = "application/json")
//    public ResponseEntity<?> getCustomerOrders() {
//        List<CustomerOrders> returnList = customerServices.getOrdNum();
//        return new ResponseEntity<>(returnList, HttpStatus.OK);
//    }

    // http://localhost:2019/customers/:id
    @GetMapping(value = "/{custid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custid) {
        Customer returnCustomer = customerServices.findCustomerById(custid);
        return new ResponseEntity<>(returnCustomer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/:likename
    @GetMapping(value = "namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findCustomersByLikeName(@PathVariable String subname) {
        List<Customer> returnList = customerServices.findByCustnameLike(subname);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count
    @GetMapping(value = "orders/count", produces = "application/json")
    public ResponseEntity<?> findByOrderCount() {
        List<OrderCounts> returnList = customerServices.getCountOrders();
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

}

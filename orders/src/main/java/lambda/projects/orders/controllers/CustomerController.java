package lambda.projects.orders.controllers;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.services.CustomerServices;
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

    // sanity check
    // http://localhost:2019/customers
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customerList = customerServices.findAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders

    // http://localhost:2019/customers/:id

    // http://localhost:2019/customers/namelike/:likename
    @GetMapping(value = "namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findCustomersByLikeName(@PathVariable String subname) {
        List<Customer> returnList = customerServices.findByCustnameLike(subname);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count

}

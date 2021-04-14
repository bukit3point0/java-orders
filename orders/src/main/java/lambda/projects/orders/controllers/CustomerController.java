package lambda.projects.orders.controllers;

import lambda.projects.orders.models.Customer;
import lambda.projects.orders.services.CustomerServices;
import lambda.projects.orders.views.CustomerOrders;
import lambda.projects.orders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody Customer newCustomer) {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custid}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(newCustomer, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/:id

    // PATCH http://localhost:2019/customers/customer/:id

    // DELETE http://localhost:2019/customers/customer/:id
    @DeleteMapping(value = "/customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custid) {
        customerServices.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

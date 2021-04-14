package lambda.projects.orders.controllers;

import lambda.projects.orders.models.Agent;
import lambda.projects.orders.models.Customer;
import lambda.projects.orders.models.Order;
import lambda.projects.orders.services.AgentServices;
import lambda.projects.orders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class OtherControllers {
    @Autowired
    private AgentServices agentServices;
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/agents/agent/:id
    @GetMapping(value = "/agents/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentid) {
        Agent returnAgent = agentServices.findAgentById(agentid);
        return new ResponseEntity<>(returnAgent, HttpStatus.OK);
    }

    // http://localhost:2019/orders/order/:id
    @GetMapping(value = "/orders/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long orderid) {
        Order returnOrder = orderServices.findOrderById(orderid);
        return new ResponseEntity<>(returnOrder, HttpStatus.OK);
    }

    // stretch
    // http://localhost:2019/orders/advanceamount

    // POST http://locahlhost:2019/orders/order
    @PostMapping(value = "/orders/order", consumes = "application/json")
    public ResponseEntity<?> updateAllOrders(@Valid
                                             @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/:id
    @PutMapping(value = "/orders/order/{orderid}", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@Valid
                                         @RequestBody Order updateOrder,
                                         @PathVariable long orderid) {
        updateOrder.setOrdnum(orderid);
        orderServices.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/orders/order/:id
    @DeleteMapping(value = "/orders/order/{orderid}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long orderid) {
        orderServices.delete(orderid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // stretch
    // DELETE http://localhost:2019/agents/unassigned/:id
}

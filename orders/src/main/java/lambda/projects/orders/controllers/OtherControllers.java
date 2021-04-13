package lambda.projects.orders.controllers;

import lambda.projects.orders.models.Agent;
import lambda.projects.orders.models.Customer;
import lambda.projects.orders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherControllers {
    @Autowired
    private AgentServices agentServices;

    // http://localhost:2019/agents/agent/:id
    @GetMapping(value = "/agents/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long agentid) {
        Agent returnAgent = agentServices.findAgentById(agentid);
        return new ResponseEntity<>(returnAgent, HttpStatus.OK);
    }

    // http://localhost:2019/orders/order/:id

    // stretch
    // http://localhost:2019/orders/advanceamount

}

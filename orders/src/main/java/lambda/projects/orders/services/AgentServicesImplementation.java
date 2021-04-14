package lambda.projects.orders.services;

import lambda.projects.orders.models.Agent;
import lambda.projects.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentService")
public class AgentServicesImplementation implements AgentServices{
    @Autowired
    private AgentRepository agentrepos;

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }

    @Override
    public Agent findAgentById(long agentcode) {
        return agentrepos
                .findById(agentcode)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + agentcode + " not found"));
    }
}

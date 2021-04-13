package lambda.projects.orders.services;

import lambda.projects.orders.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);

    // agents/agent/:id
    Agent findAgentById(long id);
}

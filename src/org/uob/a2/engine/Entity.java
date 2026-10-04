package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.util.EnumMap;
import java.util.Map;

public abstract class Entity 
{
    // fields of every entity
    protected String name;
    protected Map<ResourceType, Integer> costs = new EnumMap<>(ResourceType.class);
    protected int costAmount;
    
    // entity constructor
    public Entity(String name) 
    {
        this.name = name;
    }

    //getter method for the name field of entity
    public String getName() 
    {
        return this.name;
    }

    //setter method for the name field of entity
    public void setName(String name) {
        this.name = name;
    }

    // adds a cost requirement of an amount of a specific resource to the entity
    public void addCost(ResourceType resource, int amount) 
    {
        costs.put(resource, amount);
    }
    // returns a mapped list correlating the ResourceType to the amount (int) of it needed
    public Map<ResourceType, Integer> getCosts() 
    {
        return costs;
    }

    // method returning the costs of an entity in a formatted string form
    public String costsToString()
    {
        StringBuilder sb = new StringBuilder();
    
        costs.forEach((type, value) -> sb.append("Resource: ")
              .append(type)
              .append(" , Amount needed: ")
              .append(value)
              .append("\n")
        );
    
        return sb.toString();
    }

    public abstract String toCSV();
   
}


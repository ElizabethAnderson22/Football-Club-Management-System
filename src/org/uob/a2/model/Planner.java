package org.uob.a2.model;

import org.uob.a2.engine.Converter;
import org.uob.a2.engine.Context;
import org.uob.a2.engine.Tickable;

import java.util.Map;

public class Planner extends Converter implements Tickable
{
    // Mapping resourceTypes and their amounts for allOutputs
    private final Map<ResourceType, Integer> allOutputs = Map.of(ResourceType.MONEY, 100, ResourceType.FANS, 200);

    // planner constructor
    public Planner() 
    {
        // Base class MUST be called — pick any input/output to be the "primary" input/output. Will all be handled so doesn't matter. 
        super("Planner", ResourceType.PLAYERS, 1, ResourceType.MONEY, 1);
        this.addCost(ResourceType.PLAYERS, 11);
        this.addCost(ResourceType.EQUIPMENT, 5);
        this.addCost(ResourceType.ENERGY, 50);
    }

    // overriding tick method
    @Override
    public void tick(Context ctx) 
    {
        convert(ctx);
    }

    // overriding the convert method 
    @Override
    public void convert(Context ctx) 
    {
        Map<ResourceType, Integer> costs = this.getCosts();
    
        // First check if all inputs are available
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
        {
            ResourceType type = entry.getKey();
            int required = entry.getValue();
    
            if (ctx.state().getResourceAmount(type) < required) 
            {
                return; // Not enough resources, exit safely
            }
        }
    
        // Remove all inputs
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
        {
            ResourceType type = entry.getKey();
            int amount = entry.getValue();
            ctx.state().removeResource(type, amount);
        }

        // Produce all outputs
        for (Map.Entry<ResourceType, Integer> entry : allOutputs.entrySet()) 
        {
            ResourceType type = entry.getKey();
            int amount = entry.getValue();
            ctx.state().addResource(type, amount);
        }

    }
    
    @Override
    public String toCSV()
    {
        return getName() + ",Fans,200";
    }
}


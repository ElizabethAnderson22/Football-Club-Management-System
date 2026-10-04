package org.uob.a2.model;

import org.uob.a2.engine.Consumer;
import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;

import java.util.Map;

/**
 * Club is a special Consumer that requires multiple resources to level up.
 * It also consumes its configured resource each tick via the base Consumer logic.
 */
public class Club extends Consumer {

    public Club() {
        // Club consumes 4 STAR_PLAYERS per tick by default
        super("Club", ResourceType.STAR_PLAYERS, 4);

        // Additional costs required for level-up
        this.addCost(ResourceType.STAR_PLAYERS, 4);
        this.addCost(ResourceType.EQUIPMENT, 10);
        this.addCost(ResourceType.ENERGY, 50);
    }

    // overrides the tick method to perform both default consumption and club-specific logic
    @Override
    public void tick(Context ctx) {
        // Apply the club's regular resource consumption.
        super.consume(ctx);

        // Then apply club-specific multi-resource logic
        Map<ResourceType, Integer> costs = this.getCosts();

        // Check all required resources first
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) {
            ResourceType type = entry.getKey();
            int required = entry.getValue();
            if (ctx.state().getResourceAmount(type) < required) {
                return; // Not enough resources: do nothing
            }
        }

        // Consume all required resources
        for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) {
            ctx.state().removeResource(entry.getKey(), entry.getValue());
        }

        // Increment club level
        ctx.state().addResource(ResourceType.CLUB_LEVEL, 1);
    }

    // CSV representation of this club
    @Override
    public String toCSV() {
        return getName() + "," + consumedResource.name() + "," + amount;
    }
}

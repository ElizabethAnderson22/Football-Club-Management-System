package org.uob.a2.model;

import org.uob.a2.engine.Converter;
import org.uob.a2.engine.Context;
import org.uob.a2.engine.Tickable;

public class Coach extends Converter implements Tickable
{
    // Coach constructor 
    public Coach() {
        // Simple conversion: 1 Player to 1 Star Player
        super("Coach", ResourceType.PLAYERS, 1, ResourceType.STAR_PLAYERS, 1);
        this.addCost(ResourceType.PLAYERS, 1);
    }

    // overriding the tick method
    @Override
    public void tick(Context ctx) {
        convert(ctx);
    }

    // overriding the convert method to ensure that the specific conversion done by a coach is done
    @Override
    public void convert(Context ctx) {
        // Check input
        if (ctx.state().getResourceAmount(ResourceType.PLAYERS) < 1) {
            return; // Not enough players then exit method
        }

        // Remove input
        ctx.state().removeResource(ResourceType.PLAYERS, 1);

        // Add output
        ctx.state().addResource(ResourceType.STAR_PLAYERS, 1);
    }

    // Returns a formatted description of the converter for display
    @Override
    public String toCSV() {
        return getName() + ",StarPlayers,1";
    }
}

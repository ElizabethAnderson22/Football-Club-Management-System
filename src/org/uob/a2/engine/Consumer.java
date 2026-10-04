package org.uob.a2.engine;

import org.uob.a2.*;
import org.uob.a2.model.*;

/**
 * Abstract Consumer entity.
 * A Consumer reduces a specific resource by a fixed amount each tick.
 */
public abstract class Consumer extends Entity implements Tickable {
    // unique fields of a consumer that an entity doesn't have
    protected final ResourceType consumedResource;
    protected final int amount;

    // consumer constructor
    public Consumer(String name, ResourceType resource, int amount) {
        super(name);
        this.consumedResource = resource;
        this.amount = amount;
    }

    // getter for the consumed resource
    public ResourceType getProduct() {
        return consumedResource;
    }

    // getter for the amount of resource consumed
    public int getAmount() {
        return amount;
    }

    // overriding the entity's tick method to run the consume method
    @Override
    public void tick(Context ctx) {
        consume(ctx);
    }

    /**
     * Default consume implementation:
     * attempts to remove `amount` of `consumedResource` from the simulation state.
     * Returns true if successful, false if insufficient resources.
     */
    public boolean consume(Context ctx) {
        return ctx.state().removeResource(consumedResource, amount);
    }

    // CSV representation of this consumer
    @Override
    public String toCSV() {
        return getName() + "," + consumedResource.name() + "," + amount;
    }
}

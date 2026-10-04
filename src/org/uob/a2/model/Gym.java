package org.uob.a2.model;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Tickable;

// Gym produces 10 Fitness per tick.
public class Gym extends Producer implements Tickable {

    public Gym() {
        super("Gym", ResourceType.FITNESS, 10);
        this.addCost(ResourceType.MONEY, 100);
    }

    @Override
    public void tick(Context ctx) {
        produce(ctx);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(product, amount);
    }

    @Override
    public String toCSV() {
        return getName() + "," + product.name() + "," + amount;
    }
}



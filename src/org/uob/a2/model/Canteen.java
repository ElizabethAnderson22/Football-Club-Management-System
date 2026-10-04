package org.uob.a2.model;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Tickable;

// Canteen produces 10 Energy per tick.
public class Canteen extends Producer implements Tickable {

    public Canteen() {
        super("Canteen", ResourceType.ENERGY, 10);
        this.addCost(ResourceType.MONEY, 50);
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


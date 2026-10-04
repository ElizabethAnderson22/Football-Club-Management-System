package org.uob.a2.model;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Tickable;

// Workshop produces 5 Equipment per tick.
public class Workshop extends Producer implements Tickable {

    public Workshop() {
        super("Workshop", ResourceType.EQUIPMENT, 5);
        this.addCost(ResourceType.MONEY, 150);
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


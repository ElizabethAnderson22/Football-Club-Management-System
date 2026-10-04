package org.uob.a2.model;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Tickable;

// Academy produces 1 Player per tick.
public class Academy extends Producer implements Tickable {

    public Academy() {
        super("Academy", ResourceType.PLAYERS, 1);
        this.addCost(ResourceType.MONEY, 200);
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




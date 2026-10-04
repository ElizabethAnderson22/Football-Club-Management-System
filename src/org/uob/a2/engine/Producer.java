package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

public abstract class Producer extends Entity implements Tickable 
{
    // fields unique to a producer than any other entity doesn't have
    protected final ResourceType product;
    protected final int amount;

    // producer constructor
    public Producer(String name, ResourceType product, int amount) 
    {
        super(name);
        this.product = product;
        this.amount = amount;
    }

    // getter method for the ResourceType of the product
    public ResourceType getProduct() 
    { 
        return product; 
    }
    //getter method for the integer representing the amount produced
    public int getAmount() 
    { 
        return amount; 
    }

    // overriding the tick method to call the produce method
    @Override
    public void tick(Context ctx) {
        produce(ctx);
    }

    public abstract void produce(Context ctx);
    public abstract String toCSV();

    // toString() for debugging
    @Override
    public String toString() {
        return getName() + " produces " + amount + " " + product;
    }
}

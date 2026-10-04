package org.uob.a2.engine;

import org.uob.a2.model.ResourceType;

public abstract class Converter extends Entity implements Tickable 
{
    // fields of a consumer that an Entity don't have and are unique to a Consumer
    protected final ResourceType input;
    protected final ResourceType output;
    protected final int inputAmount;
    protected final int outputAmount;

    // converter constructor
    public Converter(String name, ResourceType input, int inputAmount, ResourceType output, int outputAmount) 
    {
        super(name);
        this.input = input;
        this.inputAmount = inputAmount;
        this.output = output;
        this.outputAmount = outputAmount;
    }

    //getter for the type of resource that is used as the input
    public ResourceType getInput() 
    { 
        return input; 
    }
    //getter for the type of resource that is used as the output
    public ResourceType getOutput() 
    { 
        return output; 
    }
    // getter for the integer representing the amount of input required
    public int getInputAmount() 
    { 
        return inputAmount; 
    }
    // getter for the integer representing the amount of output produced
    public int getOutputAmount() 
    { 
        return outputAmount; 
    }

    //overriding the tick method to call the convert method
    @Override
    public void tick(Context ctx) 
    {
        convert(ctx);
    }

    public abstract void convert(Context ctx);
    public abstract String toCSV();
}

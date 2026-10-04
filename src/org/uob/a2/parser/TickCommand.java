package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class TickCommand extends Command 
{

    public TickCommand (List<String> words) 
    {
        super(words);
    }

    @Override
    public String execute(Context context) 
    {
        // advance the simulation one tick in the main engine
        context.engine().nextTick();
        return "Tick processed. Current tick: " + context.engine().getCurrentTick();
    }

}
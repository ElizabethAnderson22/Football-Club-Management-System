package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class QuitCommand extends Command 
{

    public QuitCommand(List<String> words) 
    {
        super(words);
    }

    @Override
    public String execute(Context context) 
    {
        // Tell the engine to stop the game loop and exit the simulation
        context.engine().stop(); 
        return "Exiting the game. Goodbye!";
    }

}
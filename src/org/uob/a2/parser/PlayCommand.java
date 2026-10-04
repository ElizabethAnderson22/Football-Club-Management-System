package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class PlayCommand extends Command
{
    public PlayCommand(List<String> words)
    {
        super(words);
    }

    @Override
    public String execute(Context context)
    {
        // if a team to play against is not specified, it tells the user to enter the name
        if (words.size() < 2) 
        {
            return "You haven't entered the name of the team you wish to play. Please enter one, such as Liverpool FC";
        }
        else
        {
            // team entered after play command (accounts for multi-word names, e.g. Manchester United)
            String name = String.join(" ", words.subList(1, words.size()));

            // pass the name to the engine's play method
            return context.engine().play(name);
        }
    }
}

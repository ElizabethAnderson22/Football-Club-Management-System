package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

// Handles commands that are not recognized by the parser.
// Always returns a message indicating the command is invalid.
public class InvalidCommand extends Command 
{
    public InvalidCommand(List<String> words) 
    {
        super(words);
    }

    @Override
    public String execute(Context context) 
    {
        // Always inform the user that the command was invalid and suggest help
        return "Invalid command. Type 'help' for a list of commands.";
    }
}



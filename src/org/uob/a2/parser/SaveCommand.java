package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class SaveCommand extends Command 
{
    private final String filename;

    // Constructor takes the parsed words list
    public SaveCommand(List<String> words) 
    {
        super(words);
        // If no filename is specified, use default
        if (words == null || words.size() < 2) 
        {
            filename = "DefaultSave.txt"; // default filename
        } 
        else 
        {
            filename = words.get(1).trim(); // use the second word as filename
        }
    }

    @Override
    public String execute(Context context) 
    {
        // Attempt to save the simulation state to the given file
        try 
        {
            return context.engine().save(filename);
        }
        catch (Exception e) 
        {
            return "Failed to save: " + e.getMessage();
        }
    }
}

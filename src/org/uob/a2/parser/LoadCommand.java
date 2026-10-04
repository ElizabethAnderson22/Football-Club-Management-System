package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

// Handles loading a simulation state from a text file.
public class LoadCommand extends Command 
{
    private final String filename;

    // Constructor takes the parsed words list
    public LoadCommand(List<String> words) 
    {
        super(words);
        if (words == null || words.size() < 2) 
        {
            filename = null; // no filename provided
        } 
        else 
        {
            filename = words.get(1).trim(); // use the second word as filename
        }
    }

    @Override
    public String execute(Context context) 
    {
        if (filename == null || filename.isEmpty()) 
        {
            return "Error: No filename provided. Use this command like so: load <filename>";
        }
        if (!filename.endsWith(".txt")) 
        {
            return "Error: File must be a .txt file.";
        }


        try 
        {
            return context.engine().load(filename);
        } 
        catch (Exception e) 
        {
            return "Failed to load: " + e.getMessage();
        }
    }
}


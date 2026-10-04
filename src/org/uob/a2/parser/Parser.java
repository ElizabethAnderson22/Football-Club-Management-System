package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;
import java.util.Arrays;

// Responsible for taking raw text input from the user and turning it into a Command object.
public class Parser {

    public Command parse(String command) 
    {
        // If user pressed Enter (empty or blank), treat it as "tick"
        if (command == null || command.isBlank()) 
        {
            return new TickCommand(Arrays.asList("tick")); // simulate as if they typed "tick"
        }

        // Trim removes leading/trailing spaces
        // .split("\\s+") splits the string into words wherever there is one or more space
        // Arrays.asList converts the array into a List for easier parsing
        List<String> words = Arrays.asList(command.trim().split("\\s+"));
        String commandWord = words.get(0).toLowerCase();

        switch (commandWord) 
        {
            case "build":
            case "b":
                return new BuildCommand(words);

            case "tick":
            case "t":
                return new TickCommand(words);

            case "quit":
                return new QuitCommand(words);

            case "info":
            case "i":
                return new InfoCommand(words);

            case "cheat":
                return new CheatCommand(); // CheatCommand takes no arguments

            case "help":
                return new HelpCommand(words);

            case "graph":
            case "g":
                if (words.size() < 2) 
                {
                    // pass an empty string to GraphCommand; it will handle the error
                    return new GraphCommand("");
                }
                // join all words after the command to support multi-word resource names
                String resourceName = String.join(" ", words.subList(1, words.size()));
                return new GraphCommand(resourceName);


            case "save":
            case "s":
                if (words.size() < 2) {
                    return new InvalidCommand(words); // missing filename
                }
                return new SaveCommand(words);

            case "load":
            case "l":
                if (words.size() < 2) {
                    return new InvalidCommand(words); // missing filename
                }
                return new LoadCommand(words);

            case "play":
                return new PlayCommand(words);

            default:
                return new InvalidCommand(words);
        }
    }
}

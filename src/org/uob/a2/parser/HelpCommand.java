package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public class HelpCommand extends Command {

    // help command constructor taking in the words entered after the help command in the entry
    public HelpCommand(List<String> words) {
        super(words);
    }

    //overriding the execute method to ensure that the help command is performed
    @Override
    public String execute(Context context) {

        // if no specific help topic is specified, a general help message, listing the available commands is returned
        if (words.size() < 2) {
            return "Available commands: build|b, info|i, graph|g, save|s, load|l, tick|t, play, cheat, help, quit";
        }

        String topic = words.get(1).toLowerCase();
        // depending on what the topic of help wanted is, a different set of help is provided
        switch (topic) 
        {
            case "build":
            case "b":
                StringBuilder sb = new StringBuilder();
                sb.append("build|b <entity> - Build a new entity. Available entities:\n");
                sb.append("Producers: Gym (Cost 100 Money), Academy (Cost 200 Money), Workshop (Cost 150 Money), Canteen (Cost 50 Money)\n");
                sb.append("Converters: Coach, Planner\n");
                sb.append("Consumer: Club\n");
                sb.append("Example: build gym\n");
                return sb.toString();

            case "tick":
            case "t":
                return "tick|t - Advance the simulation by one tick. Producers generate resources, converters try to convert resources, consumers consume resources.";

            case "info":
            case"i":
                return "info|i resources|entities - Display current resources or entities.";

            case "graph":
            case "g":
                return "graph|g - Displays a text-based bar graph representing the amount of one specified resource over time.";

            case "save":
            case "s":
                return "save|s - Saves the current state of the simulation to a text file.";

            case "load":
            case "l":
                return "load|l - Loads the state of the simulation from a text file, setting it as the current state of the simulation.";

            case "play":
                return "play <ClubName> - Use Star Players, Club Level, and Energy to win trophies and rewards.";

            case "cheat":
                return "cheat - Gives you a large amount of resources to test the game.";

            default:
                return "No help available for '" + topic + "'. Type 'help' to see all commands.";
        }
    }
}

package org.uob.a2.parser;

// gives InfoCommand access to the other classes it needs
import org.uob.a2.engine.Context;
import org.uob.a2.engine.SimulationState;
import org.uob.a2.engine.Entity;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Converter;
import org.uob.a2.engine.Consumer;
import org.uob.a2.model.ResourceType;

import java.util.List;

public class InfoCommand extends Command 
{

    public InfoCommand (List<String> words) 
    {
        super(words);
    }

    @Override
    public String execute(Context context) 
    {
        // If no resource or entity is entered afer info, the user is told how to use the info command
        if (words.size() < 2) 
        {
            return "You should use the info command like follows: info <resources|entities>";
        }

        String type = words.get(1).toLowerCase();
        // creating a simulation state variable to refer to the state of the simulation currently
        SimulationState state = context.state();

        // displays resources and entities information differently
        switch (type) 
        {
            case "resources":
                return displayResources(state);
            case "entities":
                return displayEntities(state);
            default:
                return "Unknown info type. Use 'resources' or 'entities'.";
        }
    }

    // helper method
    private String displayResources (SimulationState state) 
    {
        // prints a heading of current resources along with the current state of each resource
        StringBuilder sb = new StringBuilder();
        sb.append("- Current Resources -\n");
        sb.append("Players: ").append(state.getResourceAmount(ResourceType.PLAYERS)).append("\n");
        sb.append("Fitness: ").append(state.getResourceAmount(ResourceType.FITNESS)).append("\n");
        sb.append("Energy: ").append(state.getResourceAmount(ResourceType.ENERGY)).append("\n");
        sb.append("Equipment: ").append(state.getResourceAmount(ResourceType.EQUIPMENT)).append("\n");
        sb.append("Fans: ").append(state.getResourceAmount(ResourceType.FANS)).append("\n");
        sb.append("Money: ").append(state.getResourceAmount(ResourceType.MONEY)).append("\n");
        sb.append("Star Players: ").append(state.getResourceAmount(ResourceType.STAR_PLAYERS)).append("\n");
        sb.append("Club Level: ").append(state.getResourceAmount(ResourceType.CLUB_LEVEL)).append("\n");
        sb.append("Trophies: ").append(state.getResourceAmount(ResourceType.TROPHIES)).append("\n");
        return sb.toString();
    }

    // helper method
    private String displayEntities(SimulationState state) 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("- Entities -\n");

        // gives an easy to read String name to the type of entity it is, while moving through all the entities
        for (Entity e : state.getEntities()) {

            if (e instanceof Producer p)
            {
                sb.append("Producer\nName: " + p.getName());
                sb.append(" | Product: " + p.getProduct());
                sb.append(" | Amount produced per tick: " + p.getAmount());
            }
            else if (e instanceof Converter c)
            {
                sb.append("Converter\nName: " + c.getName());
                sb.append(" | Input: " + c.getInput());
                sb.append(" | Input amount needed: " + c.getInputAmount());
                sb.append(" | Output: " + c.getOutput());
                sb.append(" | Output amount: " + c.getOutputAmount());
            }
            else if (e instanceof Consumer c)
            {
                sb.append("Consumer\nName: " + c.getName());
                sb.append(" | Resource consumed: " + c.getProduct());
                sb.append(" | Amount consumed: " + c.getAmount());
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}

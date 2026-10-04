package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;
import org.uob.a2.model.ResourceType;      
import java.util.ArrayList;           
import java.util.Map;                  


public class GraphCommand extends Command {

    private final String resource;

    //GraphCommand constructor, taking in the resource to be graphed as a String
    public GraphCommand(String resource) {
        this.resource = resource.trim().toLowerCase(); // trim whitespace
    }

    // overriding execute method to specifically perform the graphing command
    @Override
    public String execute(Context context) 
    {
        // Handling if no resource is entered
        if (resource == null || resource.isBlank()) {
            return "Please enter the name of the resource you would like to see the history of.";
        }
    
        // Try to convert user input to ResourceType
        ResourceType type;
        try {
            type = ResourceType.valueOf(resource.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Unknown resource: " + resource +
                   "\nValid resources are: money, players, energy, fitness, fans, equipment";
        }
    
        // Get the full history from the SimulationState
        List<Map<ResourceType, Integer>> fullHistory = context.state().getHistory();
    
        // If there is no history at all yet
        if (fullHistory.isEmpty()) {
            return "No history available yet for " + type + ".";
        }
    
        // Extract the history of the specified resource
        List<Integer> history = new ArrayList<>();
        for (Map<ResourceType, Integer> snapshot : fullHistory) {
            // get amount of the resource at this tick, 0 if not present
            history.add(snapshot.getOrDefault(type, 0));
        }
    
        // Build the horizontal bar graph
        StringBuilder sb = new StringBuilder();
        sb.append("Horizontal bar graph for ").append(type).append(":\n");
    
        for (int i = 0; i < history.size(); i++) {
            int value = history.get(i);
    
            // Tick number
            sb.append("Tick ").append(i + 1).append(" | ");
    
            // If value is 0 or negative
            if (value <= 0) {
                sb.append("(0)\n");
                continue;
            }
    
            // 1 star for every 5 units, rounding up
            int stars = (int) Math.ceil(value / 5.0);
            for (int j = 0; j < stars; j++) {
                sb.append("*");
            }
    
            // Print the exact amount
            sb.append(" (").append(value).append(")\n");
        }
    
        return sb.toString();
    }


}
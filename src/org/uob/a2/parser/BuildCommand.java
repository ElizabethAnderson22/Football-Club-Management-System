package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Entity;
import org.uob.a2.model.*;

import java.util.List;
import java.util.Map;

public class BuildCommand extends Command {

    public BuildCommand(List<String> words) {
        super(words);
    }

    // overriding the execute method to perform specific build function
    @Override
    public String execute(Context context) {

        // if the user hasn't specified what they want to build, they are prompted to reenter and do so
        if (words.size() < 2) 
        {
            return "Build what? (gym, academy, workshop, canteen, coach, planner, club)";
        }

        String type = words.get(1).toLowerCase();
        Entity entity;

        switch (type) 
        {
            // PRODUCERS, performs a check to see if there are sufficient funds to build the producer
            case "gym":
                entity = new Gym();
                if (context.state().canBuild(entity)) 
                {
                    context.state().addProducer("Gym");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Gym built successfully!";
                } 
                else 
                {
                    return "You need the following to build a Gym:\n" + entity.costsToString();
                }

            case "academy":
                entity = new Academy();
                if (context.state().canBuild(entity)) 
                {
                    context.state().addProducer("Academy");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Academy built successfully!";
                } 
                else 
                {
                    return "You need the following to build an Academy:\n" + entity.costsToString();
                }

            case "workshop":
                entity = new Workshop();
                if (context.state().canBuild(entity)) 
                {
                    context.state().addProducer("Workshop");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Workshop built successfully!";
                } 
                else 
                {
                    return "You need the following to build a Workshop:\n" + entity.costsToString();
                }

            case "canteen":
                entity = new Canteen ();
                if (context.state().canBuild(entity)) 
                {
                    context.state().addProducer("Canteen");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Canteen built successfully!";
                } 
                else 
                {
                    return "You need the following to build a Canteen:\n" + entity.costsToString();
                }

            // CONVERTERS - checking to see if there are sufficient resources to build
            case "coach":
                entity = new Coach();
                if (context.state().canBuild(entity))
                {
                    context.state().addConverter("Coach");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Coach hired!";
                } 
                else 
                {
                    return "You need the following to hire a Coach:\n" + entity.costsToString();
                }

            case "planner":
                entity = new Planner();
                if (context.state().canBuild(entity))
                {
                    context.state().addConverter("Planner");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Planner hired!";
                }
                else
                {
                    return "You need the following to hire a Planner:\n" + entity.costsToString();
                }

            // CONSUMERS - checking to see if there are sufficient resources to build
            case "club":
                entity = new Club();
                if (context.state().canBuild(entity))
                {
                    context.state().addConsumer("Club");
                    Map<ResourceType, Integer> costs = entity.getCosts();
                    // Loop through each resource and remove the required amount
                    for (Map.Entry<ResourceType, Integer> entry : costs.entrySet()) 
                    {
                        ResourceType resource = entry.getKey();
                        int amount = entry.getValue();
                    
                        // Remove the resource from the player's inventory - not checking boolean as have already ensure that there are sufficient resources using the canBuild method up front here. This is a better way than checking the boolean for each resource, as if one resource returned false, you would need to go back and undo any removals done previously. 
                        context.state().removeResource(resource, amount);
                    }
                    return "Club created!";
                }
                else
                {
                    return "You need the following to set up a Club:\n" + entity.costsToString();
                }

            // FALLBACK
            default:
                return "Unknown build target: " + type;
        }
    }
}
package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Engine {

    // variables needed to run the game
    private int currentTick = 0;
    private Context ctx;
    private SimulationState state;
    private boolean running = true;

    public Engine (SimulationState state)
    {
        this.state = state;
        state.updateResource(ResourceType.CREDITS, 1000);
        state.addResource(ResourceType.MONEY, 200);
    }

        public void initialiseDefaults() 
    {
                // Producers (name)
        state.addProducer("Gym");
        state.addProducer("Academy");
        state.addProducer("Workshop");
        state.addProducer("Canteen");
    
        // Converters (name)
        state.addConverter("Coach");
        state.addConverter("Planner");
    
        // Consumer (name)
        state.addConsumer("Club");

        //Resources
        state.addResource(ResourceType.PLAYERS, 0);
        state.addResource(ResourceType.FITNESS, 0);
        state.addResource(ResourceType.EQUIPMENT, 0);
        state.addResource(ResourceType.ENERGY, 0);
        state.addResource(ResourceType.FANS, 0);
        state.addResource(ResourceType.MONEY, 0);
        state.addResource(ResourceType.STAR_PLAYERS, 0);
        state.addResource(ResourceType.CLUB_LEVEL, 0);
            }
    //getter for the SimulationState variable tied to the Engine
    public SimulationState getState ()
    {
        return state; 
    }
    //getter for the integer representing the current tick
    public int getCurrentTick()
    {
        return currentTick;
    }

    // runs the next tick, updating entities, degrading resources, then recording history
    public String nextTick() 
    {
        currentTick++;
        Context ctx = new Context(this, state);
    
        // Producers
        for (Producer p : state.getProducers()) {
            p.tick(ctx);
        }
    
        // Converters
        for (Converter c : state.getConverters()) {
            c.tick(ctx);
        }
    
        // Consumers
        for (Consumer c : state.getConsumers()) {
            c.tick(ctx);
        }
    
        // Degrade 
        if (state.removeResource(ResourceType.ENERGY, 5))
        {
            
        }
        else
        {
            state.updateResource(ResourceType.ENERGY, 0);
        }
        if (state.removeResource(ResourceType.FITNESS, 2))
        {
            
        }
        else
        {
            state.updateResource(ResourceType.FITNESS, 0);
        }
    
        // Record history after all updates
        state.updateHistory();
    
        return "Tick " + currentTick + " completed.";
    }

    // setter method taking in an integer for the tick to be set to
    public void setCurrentTick(int t) 
    { 
        this.currentTick = t; 
    }
    // method that sets the tick back to 0, as if the game were to restart, called when the final command play is done
    public void resetTick() 
    { 
        this.currentTick = 0; 
    }
    // method calling the save method in the SaveLoadManager to save the state of the simulation to a text file
    public String save (String filename) throws IOException
    {
        return SaveLoadManager.save(this, filename);
    }
    // method calling the load method in the SaveLoadManager to load the simulation state from a text file
    public String load (String filename) throws IOException
    {
        return SaveLoadManager.load(this, filename);
    }
    // method stopping the running of the simulation
    public void stop ()
    {
        running = false;
    }
    // method restarting/starting the running of the simulation
    public void start() 
    { 
        running = true; 
    }

    // method returning a boolean indicating whether or not the simulation is still running, i.e. if the user still wants to play
    public boolean isRunning ()
    {
        return running;
    }
    // method that performs the final play command 
    public String play(String teamName) 
    {
        int clubLevel = state.getResourceAmount(ResourceType.CLUB_LEVEL);
        int stars = state.getResourceAmount(ResourceType.STAR_PLAYERS);
        int energy = state.getResourceAmount(ResourceType.ENERGY);

        //checks the requirements to run the final play command
        if (clubLevel < 3) return "You must reach Club Level 3 before playing the final match!";
        if (stars <= 0) return "Your club has no Star Players! Recruit some before attempting the final game.";
        if (energy <= 0) return "Your club has no Energy left! Restore energy before attempting the final match.";

        // uses the resources needed to run play command
        if (state.removeResource(ResourceType.STAR_PLAYERS, 5))
        {
            
        }
        else return "Insufficient Star Players to play the Championship game!";
        if (state.removeResource(ResourceType.ENERGY, 5))
        {
            
        }
        else return "Insufficient energy to play the Championship game!";

        // adds the credits/benefits of running play command
        state.addResource(ResourceType.TROPHIES, 1);
        state.addResource(ResourceType.MONEY, 500);
        state.addResource(ResourceType.FANS, 100);

        // resets the game essentially back to the start
        state.updateResource(ResourceType.CLUB_LEVEL, 0);
        resetTick();

        //prints message to user telling them what has happened
        return "Congratulations!\n"
             + "You have WON the championship!\n"
             + "Your Star Players and Energy powered you to victory.\n"
             + "Rewards earned:\n"
             + " - 1 Trophy\n"
             + " - 500 Money\n"
             + " - 100 Fans\n";
    }
}
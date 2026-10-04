package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        // Create simulation state
        SimulationState state = new SimulationState ();

        // Create engine using Simulation State
        Engine engine = new Engine(state);

        // Create context object (wraps engine and state together)
        Context context = new Context(engine, state);

        // Create parser
        Parser parser = new Parser();

        // Setup input loop
        Scanner scanner = new Scanner(System.in);

        //Printing message at start of simulation to explain to user
        System.out.println("Welcome to the Football Club Management Simulation!\n");
        System.out.println("Your goal is to build and manage a successful club, train star players, and ultimately play and win the championship.\n");
        
        System.out.println("How it works:");
        System.out.println("- You start with some basic resources: Money, Players, Fitness, Energy, Equipment, and Fans.");
        System.out.println("- Use Producers (Gym, Academy, Workshop, Canteen) to generate resources each tick.");
        System.out.println("- Use Converters (Coach, Planner) to upgrade your team or convert resources into Money and Fans.");
        System.out.println("- The Club consumes resources (Star Players, Equipment, Energy) to increase its Club Level.");
        System.out.println("- Only when your Club reaches Level 3 and you have enough Star Players and Energy can you play the final championship game.\n");
        
        System.out.println("Commands:");
        System.out.println("- build <entity>: Construct a new Producer, Converter, or Club upgrade.");
        System.out.println("- play <ClubName>: Compete in the final championship match (requires Level 3 and enough resources).");
        System.out.println("- cheat: Add resources for testing purposes.");
        System.out.println("- tick: Advance the simulation by one tick.");
        System.out.println("- info: Display your current resources and entities.");
        System.out.println("- graph <resource>: See a text-based graph of a resource over time.");
        System.out.println("- save <filename>: Save your current game state to a text file .");
        System.out.println("- load <filename>: Load a previously saved game state from a text file.");
        System.out.println("- quit: Exit the simulation.\n");
        
        System.out.println("Plan wisely, manage your resources, and lead your club to victory!");
        System.out.println("You have been gifted 200 units of money, use it wisely!");
        System.out.println("Let the simulation begin!\n");


        while (engine.isRunning()) 
        {
            System.out.print("> "); // prompt user to enter input
            String input = scanner.nextLine();

            // Parse the input into a command
            Command command = parser.parse(input);

            // Execute the command using the context
            String output = command.execute(context);

            // Display the output
            System.out.println(output);
        }

        scanner.close();
    }
} 
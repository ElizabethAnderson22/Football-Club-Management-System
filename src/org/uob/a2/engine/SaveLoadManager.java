package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;

public class SaveLoadManager {

    // Save the simulation state to a file
    public static String save(Engine engine, String filename) throws IOException {
        SimulationState state = engine.getState();
        Path fileOut = Paths.get("data/" + filename);

        try (BufferedWriter writer = Files.newBufferedWriter(fileOut, CREATE, TRUNCATE_EXISTING)) {

            // Save current tick
            writer.write("CurrentTick," + engine.getCurrentTick());
            writer.newLine();

            // Save resources (no "Resource," prefix)
            Map<ResourceType, Integer> inventory = state.getInventory();
            for (ResourceType type : ResourceType.values()) {
                int amount = inventory.getOrDefault(type, 0);
                writer.write(type.name() + "," + amount);
                writer.newLine();
            }

            // Save producers
            for (Producer p : state.getProducers()) {
                writer.write("Producer," + p.toCSV());
                writer.newLine();
            }

            // Save converters
            for (Converter c : state.getConverters()) {
                writer.write("Converter," + c.toCSV());
                writer.newLine();
            }

            // Save consumers
            for (Consumer c : state.getConsumers()) {
                writer.write("Consumer," + c.toCSV());
                writer.newLine();
            }

            return "Successfully saved to " + filename;
        }
    }

    // Load the simulation state from a file
    public static String load(Engine engine, String filename) throws IOException {
        Path fileIn = Paths.get("data/" + filename);

        try (BufferedReader reader = Files.newBufferedReader(fileIn)) {
            SimulationState state = engine.getState();

            // Reset the simulation to empty/default state
            state.newSimulation();
            engine.resetTick();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", 2);
                String key = parts[0];

                // Current tick
                if (key.equalsIgnoreCase("CurrentTick")) {
                    int tick = Integer.parseInt(parts[1]);
                    engine.setCurrentTick(tick);
                }
                // Producer
                else if (key.equalsIgnoreCase("Producer")) {
                    String[] producerParts = parts[1].split(",");
                    String rawName = producerParts[0];
                    String baseName = rawName.split(" ")[0];
                    state.addProducer(baseName);
                }
                // Converter
                else if (key.equalsIgnoreCase("Converter")) {
                    String[] converterParts = parts[1].split(",");
                    String rawName = converterParts[0];
                    String baseName = rawName.split(" ")[0];
                    state.addConverter(baseName);
                }
                // Consumer
                else if (key.equalsIgnoreCase("Consumer")) {
                    String[] consumerParts = parts[1].split(",");
                    String rawName = consumerParts[0];
                    String baseName = rawName.split(" ")[0];
                    state.addConsumer(baseName);
                }
                // Resource line (e.g. "METAL,50")
                else {
                    try {
                        ResourceType res = ResourceType.valueOf(key.toUpperCase());
                        int amount = Integer.parseInt(parts[1]);
                        state.updateResource(res, amount);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Unknown resource in file: " + key);
                    }
                }
            }

            return "Successfully loaded from " + filename;
        }
    }
}

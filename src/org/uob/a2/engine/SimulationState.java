package org.uob.a2.engine;

import org.uob.a2.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;


 //SimulationState manages the current state of the simulation.
 //It tracks producers, converters, consumers, resources, and resource history.
 // Provides methods to add, update, and remove resources, as well as reset the simulation.
public class SimulationState {

    // Entities in the simulation
    private List<Producer> producers = new ArrayList<>();
    private List<Converter> converters = new ArrayList<>();
    private List<Consumer> consumers = new ArrayList<>();

    // Resource inventory (all ResourceTypes mapped to integer amounts)
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);

    // History of resource snapshots at each tick
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    // Constructor initializes inventory with default values.
    public SimulationState() {
        initInventoryDefaults();
    }


    // Reset simulation to default state.
    // Clears entities and history, and reinitializes inventory.
    public void newSimulation() {
        inventory.clear();
        producers.clear();
        converters.clear();
        consumers.clear();
        resourceHistory.clear();
        initInventoryDefaults();
    }


    
    //Initialize inventory with all ResourceTypes set to 0,
    //and starting credits set to 1000.
    private void initInventoryDefaults() {
        for (ResourceType rt : ResourceType.values()) {
            inventory.put(rt, 0);
        }
    }

    // Accessors for entities

    public List<Producer> getProducers() { return producers; }
    public List<Converter> getConverters() { return converters; }
    public List<Consumer> getConsumers() { return consumers; }

    // Resource management

    // Add a resource amount to the inventory.
    // Negative amounts are ignored.
    public void addResource(ResourceType resource, int amount) {
        int current = inventory.getOrDefault(resource, 0);
        inventory.put(resource, current + Math.max(0, amount));
    }

     // Update a resource to a specific amount.
     // Negative values are clamped to 0.
    public void updateResource(ResourceType resource, int amount) {
        inventory.put(resource, Math.max(0, amount));
    }

     // Get the current amount of a resource.
     // Returns 0 if the resource is not found.
    public int getResourceAmount(ResourceType resource) {
        return inventory.getOrDefault(resource, 0);
    }

     // Attempt to remove a specified amount of a resource.
     // Returns true if successful, false if insufficient.
    public boolean removeResource(ResourceType resource, int amount) {
        int current = inventory.getOrDefault(resource, 0);
        if (amount < 0) return false;
        if (current < amount) return false;
        inventory.put(resource, current - amount);
        return true;
    }

     // Return the full inventory map.
    public Map<ResourceType, Integer> getInventory() {
        return inventory;
    }

    // History management

    // Record a snapshot of the current inventory into history.
    public void updateHistory() 
    {
        Map<ResourceType, Integer> snapshot = new EnumMap<>(ResourceType.class);
        for (ResourceType rt : ResourceType.values()) {
            snapshot.put(rt, inventory.getOrDefault(rt, 0));
        }
        resourceHistory.add(snapshot);
    }

    // Get the history of resource snapshots.
    // Returns a copy of each snapshot to avoid external modification.
    public List<Map<ResourceType, Integer>> getHistory() {
        List<Map<ResourceType, Integer>> history = new ArrayList<>();
        for (Map<ResourceType, Integer> snapshot : resourceHistory) {
            history.add(new EnumMap<>(snapshot));
        }
        return history;
    }

    // Entity numbering helpers

    public void numberProducers() {
        for (int i = 0; i < producers.size(); i++) {
            Producer p = producers.get(i);
            int count = 1;
            for (int j = 0; j < i; j++) {
                Producer earlier = producers.get(j);
                if (getType(earlier.getName()).equalsIgnoreCase(getType(p.getName()))) {
                    count++;
                }
            }
            p.setName(getType(p.getName()) + " " + count);
        }
    }

    public void numberConverters() {
        for (int i = 0; i < converters.size(); i++) {
            Converter c = converters.get(i);
            int count = 1;
            for (int j = 0; j < i; j++) {
                Converter earlier = converters.get(j);
                if (getType(earlier.getName()).equalsIgnoreCase(getType(c.getName()))) {
                    count++;
                }
            }
            c.setName(getType(c.getName()) + " " + count);
        }
    }

    public void numberConsumers() {
        for (int i = 0; i < consumers.size(); i++) {
            Consumer c = consumers.get(i);
            int number = i + 1;
            c.setName("Consumer " + number);
        }
    }

    // Entity creation

    public void addProducer(String name) {
        Producer prod;
        switch (name.toLowerCase()) {
            case "gym": prod = new Gym(); break;
            case "academy": prod = new Academy(); break;
            case "workshop": prod = new Workshop(); break;
            case "canteen": prod = new Canteen(); break;
            default:
                System.out.println("Unknown producer name entered.");
                return;
        }
        producers.add(prod);
        numberProducers();
    }

    public void addConverter(String name) {
        Converter con;
        switch (name.toLowerCase()) {
            case "coach": con = new Coach(); break;
            case "planner": con = new Planner(); break;
            default:
                System.out.println("Unknown converter entered.");
                return;
        }
        converters.add(con);
        numberConverters();
    }

    public void addConsumer(String name) {
        Consumer cons;
        switch (name.toLowerCase()) {
            case "club": cons = new Club(); break;
            default:
                System.out.println("Unknown consumer entered.");
                return;
        }
        consumers.add(cons);
        numberConsumers();
    }

    // Return a combined list of all entities.
    public List<Entity> getEntities() {
        List<Entity> list = new ArrayList<>();
        list.addAll(producers);
        list.addAll(converters);
        list.addAll(consumers);
        return list;
    }

    // Check if the player can afford to build a given entity.
    public boolean canBuild(Entity entity) {
        Map<ResourceType, Integer> costs = entity.getCosts();
        for (ResourceType type : costs.keySet()) {
            int required = costs.get(type);
            int available = inventory.getOrDefault(type, 0);
            if (available < required) {
                return false;
            }
        }
        return true;
    }

    // Helper method for entity naming 
    private static String getType(String name) {
        int lastSpace = name.lastIndexOf(' ');
        if (lastSpace == -1) {
            return name;
        }
        return name.substring(0, lastSpace);
    }
}

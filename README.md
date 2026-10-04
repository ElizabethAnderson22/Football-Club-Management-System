# Football Club Management Simulation

A Java-based command-line football club management simulation built using object-oriented design. The player manages resources, develops facilities, improves the club and works towards winning the championship.

The project was designed around a modular simulation engine, with different entities responsible for producing, converting and consuming resources as the club develops.

## Demo

A short demonstration video will be added here.

The demonstration shows the simulation being run from the command line, including resource management, facility construction, simulation ticks and progression towards the championship.

## Overview

The simulation models a football club as a resource-driven system.

Each game tick updates the state of the club by running its producers, converters and consumers. The player must balance resources such as money, energy and player development while investing in facilities and progressing the club towards the championship.

The main objective is to:

1. Develop the club to Level 3.
2. Build enough Star Players.
3. Maintain sufficient Energy.
4. Successfully play the championship match.

## Key Features

- Command-line interface with structured command parsing
- Tick-based simulation engine
- Resource production, conversion and consumption
- Facility construction and club progression
- Player development and fitness management
- Star Player and Club Level progression
- Championship gameplay and rewards
- Save/load functionality using text files
- Resource history graphs for monitoring simulation behaviour
- Debug/cheat functionality for rapidly testing different game states

## Technical Design

### Object-Oriented Architecture

The simulation uses an inheritance-based entity model to separate different behaviours within the game.

- `Entity` provides shared functionality for simulation entities.
- `Producer` represents entities that generate resources each tick.
- `Converter` represents entities that transform resources.
- `Consumer` represents entities that consume resources.
- Concrete implementations such as `Gym`, `Academy`, `Workshop`, `Canteen`, `Coach`, `Planner` and `Club` build on these abstractions.

This structure allows different entities to share common behaviour while implementing their own resource-management logic.

### Simulation Engine

`Engine` coordinates the simulation by processing each game tick.

During a tick, producers, converters and consumers are updated before resource degradation is applied and the resulting state is recorded. This provides a central mechanism for progressing the simulation consistently.

### Command Parsing

User input is processed by `Parser` and converted into command objects.

Separate command classes handle operations including:

- Building facilities
- Advancing simulation ticks
- Displaying resource information
- Generating resource graphs
- Saving and loading game states
- Playing the championship

This separates user interaction from the underlying simulation logic.

### Persistence

`SaveLoadManager` provides save and load functionality by serialising the simulation state to text files and reconstructing the state when a saved game is loaded.

## Technologies and Concepts

- Java
- Object-oriented programming
- Abstraction
- Inheritance
- Polymorphism
- Collections
- Enums
- File I/O
- Command-line interfaces
- State management
- Simulation design

## Running the Project

### Requirements

- Java Development Kit (JDK) 21 or later

### Using the run script

From the project root:

```bash
./run.sh
```

If required, make the script executable first:

```bash
chmod +x run.sh
./run.sh
```

### Manual compilation

Alternatively:

```bash
mkdir -p out data
javac -d out src/org/uob/a2/*.java src/org/uob/a2/engine/*.java src/org/uob/a2/parser/*.java src/org/uob/a2/model/*.java
java -cp out org.uob.a2.Main
```

## Example Commands

```text
help
info resources
build academy
tick
graph money
save mySave.txt
load mySave.txt
play Manchester United
```

## Project Structure

```text
src/
└── org/uob/a2/
    ├── Main.java
    │
    ├── engine/
    │   ├── Consumer.java
    │   ├── Context.java
    │   ├── Converter.java
    │   ├── Engine.java
    │   ├── Entity.java
    │   ├── Producer.java
    │   ├── SaveLoadManager.java
    │   ├── SimulationState.java
    │   └── Tickable.java
    │
    ├── model/
    │   ├── Academy.java
    │   ├── Canteen.java
    │   ├── Club.java
    │   ├── Coach.java
    │   ├── Planner.java
    │   ├── ResourceType.java
    │   └── Workshop.java
    │
    └── parser/
        ├── BuildCommand.java
        ├── CheatCommand.java
        ├── Command.java
        ├── GraphCommand.java
        ├── HelpCommand.java
        ├── InfoCommand.java
        ├── InvalidCommand.java
        ├── LoadCommand.java
        ├── Parser.java
        ├── PlayCommand.java
        ├── QuitCommand.java
        ├── SaveCommand.java
        └── TickCommand.java
```

## Development and Learning

This project provided practical experience designing a larger Java application using object-oriented principles rather than implementing functionality in a single program.

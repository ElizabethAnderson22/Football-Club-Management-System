# Football Club Management Simulation

A command-line football club management simulation built in Java using object-oriented design. The player manages resources, builds facilities, develops the club and works towards winning the championship.

## Demo

A short demonstration video will be added here.

## Overview

The simulation models a football club as a resource-driven system. Each game tick updates the state of the club by running its producers, converters and consumers, while resource history can be tracked over time.

The main objective is to develop the club to Level 3, build enough Star Players and maintain sufficient Energy to play the championship match.

## Features

- Command-line interface with command parsing
- Tick-based simulation engine
- Resource production, conversion and consumption
- Facility construction and club progression
- Player and fitness management
- Star Player and Club Level progression
- Championship gameplay and rewards
- Save and load functionality using text files
- Text-based resource history graphs
- Debug/cheat command for rapidly testing game states

## Technical Design

### Object-oriented architecture

The simulation uses an inheritance-based entity model:

- `Entity` provides shared functionality for simulation entities.
- `Producer` represents entities that generate resources each tick.
- `Converter` represents entities that transform one resource into another.
- `Consumer` represents entities that consume resources.
- Concrete classes such as `Gym`, `Academy`, `Workshop`, `Canteen`, `Coach`, `Planner` and `Club` extend these abstractions.

### Simulation engine

`Engine` coordinates each simulation tick, updating producers, converters and consumers before applying resource degradation and recording the resulting state.

### Command parsing

User input is converted into command objects through the `Parser`. Separate command classes handle operations such as building entities, advancing ticks, displaying information, graphing resources, saving/loading and playing the championship.

### Persistence

`SaveLoadManager` serialises the simulation state to text files and reconstructs the state when a saved game is loaded.

## Technologies

- Java
- Object-oriented programming
- Inheritance and polymorphism
- Collections and enums
- File I/O
- Command-line interfaces

## Running the Project

### Requirements

- Java Development Kit (JDK) 21 or later

### Run

```bash
./run.sh
```

Alternatively, compile and run manually:

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
    ├── model/
    │   ├── Academy.java
    │   ├── Canteen.java
    │   ├── Club.java
    │   ├── Coach.java
    │   ├── Gym.java
    │   ├── Planner.java
    │   ├── ResourceType.java
    │   └── Workshop.java
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

## Notes

This repository contains the implementation of the project. University-provided assessment tests and coursework scaffolding are intentionally not included in the public repository.

## Future Improvements

- Add a graphical interface
- Introduce richer match outcomes and opponent difficulty
- Add persistent player profiles and squad management
- Expand automated tests with project-specific test cases

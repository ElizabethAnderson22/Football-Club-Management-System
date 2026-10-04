package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;

public class CheatCommand extends Command {

    // Cheat amounts as constants, providing enough resources to user to test it quickly
    private static final int MONEY_CHEAT = 2000;
    private static final int PLAYERS_CHEAT = 50;
    private static final int FITNESS_CHEAT = 100;
    private static final int EQUIPMENT_CHEAT = 90;
    private static final int ENERGY_CHEAT = 150;

    public CheatCommand() {
        super();
    }

    @Override
    public String execute(Context context) {

        // Apply cheat resources to inventory
        context.state().addResource(ResourceType.MONEY, MONEY_CHEAT);
        context.state().addResource(ResourceType.PLAYERS, PLAYERS_CHEAT);
        context.state().addResource(ResourceType.FITNESS, FITNESS_CHEAT);
        context.state().addResource(ResourceType.EQUIPMENT, EQUIPMENT_CHEAT);
        context.state().addResource(ResourceType.ENERGY, ENERGY_CHEAT);

        return "Cheat activated! Money, Players, Fitness, Equipment, and Energy increased.";
    }
}


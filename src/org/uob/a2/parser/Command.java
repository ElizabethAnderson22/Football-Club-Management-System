package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import java.util.List;

public abstract class Command {

    protected List<String> words;

    // Constructor for commands with arguments
    public Command(List<String> words) {
        this.words = words;
    }

    // Default constructor for commands without arguments
    public Command() {}

    // All commands must implement this method
    public abstract String execute(Context ctx);
}

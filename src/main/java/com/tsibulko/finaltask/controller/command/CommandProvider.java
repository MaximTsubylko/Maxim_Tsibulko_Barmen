package com.tsibulko.finaltask.controller.command;

import com.tsibulko.finaltask.controller.command.impl.CreateNewCocktilCommand;
import com.tsibulko.finaltask.controller.command.impl.TestCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {

        commandMap.put("hello", new TestCommand());
        commandMap.put("register_cocktil", new CreateNewCocktilCommand());
    }

    /**
     * Return command by name
     *
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
        return commandMap.get(command);
    }
}
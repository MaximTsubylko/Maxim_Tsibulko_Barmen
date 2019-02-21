package com.tsibulko.finaltask.controller.command;

import com.tsibulko.finaltask.controller.command.impl.*;

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
        commandMap.put("show_create_coctil", new ViewCreateCocktailFormCommand());
        commandMap.put("main", new ShowEmptyMainPageCommand());
        commandMap.put("register_cocktil", new CreateNewCocktailCommand());
        commandMap.put("cocktil_list", new ViewCocktailListCommand());
        commandMap.put("view_cocktil_details", new ViewCocktailDetailCommand());
        commandMap.put("delete_cocktil", new DeleteCocktailCommand());
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
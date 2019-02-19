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
        commandMap.put("show_create_coctil", new ViewCreateCoctileFormCommand());
        commandMap.put("main", new ShowEmptyMainPageCommand());
        commandMap.put("register_cocktil", new CreateNewCocktilCommand());
        commandMap.put("cocktil_list", new ViewCoctilListCommand());
        commandMap.put("view_cocktil_details", new ViewCocktileDetailCommand());
        commandMap.put("delete_cocktil", new DeleteCoctilCommand());
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
package com.tsibulko.finaltask.command;

import com.tsibulko.finaltask.command.impl.*;

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
        commandMap.put("show_create_cocktail", new ViewCreateCocktailFormCommand());
        commandMap.put("main", new ShowEmptyMainPageCommand());
        commandMap.put("register_cocktail", new CreateNewCocktailCommand());
        commandMap.put("cocktail_list", new ViewCocktailListCommand());
        commandMap.put("view_cocktail_details", new ViewCocktailDetailCommand());
        commandMap.put("delete_cocktail", new DeleteCocktailCommand());
        commandMap.put("try_login", new TryLoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("show_login_page", new ShowLoginPageCommand());
        commandMap.put("show_success_page", new ShowSuccessPageCommand());
        commandMap.put("send_recovery_message", new RecoverySendMessageCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("show_profile", new ShowProfilePage());
        commandMap.put("show_edit_page", new ShowEditPageCommand());
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
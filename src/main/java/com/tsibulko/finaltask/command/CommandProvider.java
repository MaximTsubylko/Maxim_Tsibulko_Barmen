package com.tsibulko.finaltask.command;

import com.tsibulko.finaltask.command.impl.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<CommandEnum, Command> commandMap = new EnumMap<>(CommandEnum.class);

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put(CommandEnum.SHOW_CREATE_COCKTAIL, new ViewCreateCocktailFormCommand());
        commandMap.put(CommandEnum.MAIN, new ShowEmptyMainPageCommand());
        commandMap.put(CommandEnum.REGISTER_COCKTAIL, new CreateNewCocktailCommand());
        commandMap.put(CommandEnum.COCKTAIL_LIST, new ViewCocktailListCommand());
        commandMap.put(CommandEnum.SHOW_COCKTAIL_DETAILS, new ViewCocktailDetailCommand());
        commandMap.put(CommandEnum.DELETE_COCKTAIL, new DeleteCocktailCommand());
        commandMap.put(CommandEnum.TRY_LOGIN, new TryLoginCommand());
        commandMap.put(CommandEnum.REGISTRATION, new RegistrationCommand());
        commandMap.put(CommandEnum.SHOW_ERROR_PAGE, new ShowErrorPage());
        commandMap.put(CommandEnum.SHOW_LOGIN_PAGE, new ShowLoginPageCommand());
        commandMap.put(CommandEnum.SHOW_SUCCESS_PAGE, new ShowSuccessPageCommand());
        commandMap.put(CommandEnum.SEND_RECOVERY_MESSAGE, new RecoverySendMessageCommand());
        commandMap.put(CommandEnum.LOGOUT, new LogoutCommand());
        commandMap.put(CommandEnum.SHOW_PROFILE, new ShowProfilePage());
        commandMap.put(CommandEnum.SHOW_EDIT_PAGE, new ShowEditPageCommand());
    }

    /**
     * Return command by name
     *
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(CommandEnum command) {

        return commandMap.get(command);
    }
}
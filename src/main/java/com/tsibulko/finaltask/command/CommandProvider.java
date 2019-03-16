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
        commandMap.put(CommandEnum.SHOW_PROFILE, new ShowProfilePageCommand());
        commandMap.put(CommandEnum.SHOW_EDIT_PAGE, new ShowEditPageCommand());
        commandMap.put(CommandEnum.SHOW_INDEX_PAGE, new ShowIndexCommand());
        commandMap.put(CommandEnum.ACTIVATE_USER, new UserActivationCommand());
        commandMap.put(CommandEnum.RESTORE_PASSWORD, new RestorePasswordCommand());
        commandMap.put(CommandEnum.EDIT_PROFILE, new EditProfileCommand());
        commandMap.put(CommandEnum.SHOW_UPDATE_COCKTAIL_PAGE, new ShowEditCocktailPage());
        commandMap.put(CommandEnum.EDIT_COCKTAIL, new EditCocktailCommand());
        commandMap.put(CommandEnum.CHANGE_PASSWORD, new ChangePasswordCommand());
        commandMap.put(CommandEnum.SHOW_CUSTOMER_LIST, new ShowCustomerListCommand());
        commandMap.put(CommandEnum.DELETE_CUSTOMER, new DeleteCustomerCommand());
        commandMap.put(CommandEnum.ADD_USER_FEEDBACK, new AddUserFeedbackCommand());
        commandMap.put(CommandEnum.ADD_COCKTAIL_FEEDBACK, new AddCocktailFedbackCommand());
        commandMap.put(CommandEnum.SHOW_INGREDIENT_LIST, new ShowIngredientListCommand());
        commandMap.put(CommandEnum.SHOW_CREATE_INGREDIENT,new ShowCreateIngredientPageCommand());
        commandMap.put(CommandEnum.CREATE_INGREDIENT, new CreateIngredientCommand());
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
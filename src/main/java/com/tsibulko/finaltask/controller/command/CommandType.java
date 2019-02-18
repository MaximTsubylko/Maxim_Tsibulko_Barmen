package com.tsibulko.finaltask.controller.command;
import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {

    CREATE_COCKTIL;

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }
}

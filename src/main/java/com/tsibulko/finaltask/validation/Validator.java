package com.tsibulko.finaltask.validation;

public interface Validator {
    default boolean doValid() {
        return false;
    }
}

package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Customer;

public class CustomerValidator implements Validator<Customer> {
    @Override
    public void doValidation(Customer entity) throws ValidationException {
        FieldValidator validator = FieldValidator.getInstance();
        validator.simpleStingMatches(entity.getLogin(), 45, "login.registrationbutton");
        validator.simpleStingMatches(entity.getPassword(), 45, "password");
        validator.emailMatches(entity.getEmail());

    }
}

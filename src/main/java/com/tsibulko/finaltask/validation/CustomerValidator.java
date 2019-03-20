package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.service.ServiceException;

public class CustomerValidator implements Validator<Customer> {
    @Override
    public void doValidation(Customer entity) throws ValidationException, ServiceException {
        FieldValidator validator = FieldValidator.getInstance();
        try {
            validator.isUnique(new String[]{"login"}, Customer.class, entity.getLogin());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        validator.simpleStingMatches(entity.getLogin(),20 , "login");
        validator.simpleStingMatches(entity.getPassword(), 20, "password");
//        validator.emailMatches(entity.getEmail());

    }
}

package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Customer;

public interface LoginAndRegistrationValid extends Validator {
    boolean isUniqueCustomer(Customer customer) throws LoginAndRegistrationException;

    boolean isExistCustomer(String login) throws LoginAndRegistrationException;

    boolean isExistEmail(String email) throws LoginAndRegistrationException;

}

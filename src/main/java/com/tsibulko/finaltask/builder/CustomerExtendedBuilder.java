package com.tsibulko.finaltask.builder;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface CustomerExtendedBuilder extends Builder<Customer> {
    Customer buildForEdit(HttpServletRequest request) throws ServiceException;
    Customer buildForEnter(HttpServletRequest request) throws ServiceException;

}

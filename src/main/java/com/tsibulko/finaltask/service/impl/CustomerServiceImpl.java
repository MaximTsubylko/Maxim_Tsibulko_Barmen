package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.service.*;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.EncryptPassword;
import com.tsibulko.finaltask.util.StringGenerator;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CustomerDAO dao;


    public void setNewState(HttpServletRequest request, String name) throws ServiceException {
        Integer userID = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
        String reaquestKey = request.getParameter(AppConstant.VALUE_PARAMETR);
        UserKey userKey = UserKey.getInstance();
        if (reaquestKey.equals(userKey.get(userID))) {
            Customer customer = getByPK(userID);
            customer.setState(UserState.getByName(name).getId());
            update(customer);
        } else {
            throw new ServiceException("Error in change state!");
        }
    }

    public void editUserProfile(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);
        customer.setFirst_name(request.getParameter(AppConstant.FIRST_NAME_PARAMETR));
        customer.setSecond_name(request.getParameter(AppConstant.SECOND_NAME_PARAMETR));
        customer.setEmail(request.getParameter(AppConstant.EMAIL_PARAMETR));
        update(customer);
        session.setAttribute(AppConstant.SESSION_ATTRIBUTE, getByPK(customer.getId()));
    }

    public void restorePassword(HttpServletRequest request) throws ServiceException {
        StringGenerator generator = new StringGenerator();
        HttpSession session = request.getSession();
        String newPassword = generator.generate();
        Integer userID = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
        String reaquestKey = request.getParameter(AppConstant.VALUE_PARAMETR);
        UserKey userKey = UserKey.getInstance();
        if (reaquestKey.equals(userKey.get(userID))) {
            Customer customer = getByPK(userID);
            customer.setPassword(newPassword);
            request.setAttribute(AppConstant.NEW_PASSWORD_PARAMETR, newPassword);
            EncryptPassword.encrypt(customer);
            update(customer);
            session.setAttribute(AppConstant.SESSION_ATTRIBUTE, customer);
        } else {
            throw new ServiceException("Error in change password!");
        }
    }

    public Customer changePassword(Customer customer, String newPassword) throws ServiceException {
        customer.setPassword(newPassword);
        EncryptPassword.encrypt(customer);
        update(customer);
        return customer;
    }

    public Customer getCustomerWithCocktails(Integer id,Customer customer) throws ServiceException {
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        customer = getByPK(id);
        customer.setCocktails(cocktailService.getCocktailByCustomer(customer));
        return customer;
    }


    @Override
    public Customer create(Customer customer) throws ServiceException {
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isUnique(new String[]{AppConstant.LOGIN_PARAMENR, AppConstant.EMAIL_PARAMETR},
                    Customer.class, customer.getLogin(), customer.getEmail());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            EncryptPassword.encrypt(customer);
            dao.persist(customer);
            return customer;
        } catch (DaoException e) {
            throw new ServiceException(e, "Create customer error");
        } catch (ValidationException e) {
            throw new ServiceException("This user not unique");
        }
    }

    @Override
    public void delete(Customer customer) throws ServiceException {
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isExist(AppConstant.LOGIN_PARAMENR, Customer.class, customer.getLogin());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.delete(customer);

        } catch (DaoException e) {
            throw new ServiceException(e, "delete error");
        } catch (ValidationException e) {
            throw new ServiceException("This customer not exist");
        }
    }

    @Override
    public Customer getByPK(Integer id) throws ServiceException {
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (dao.getByPK(id).isPresent()) {
                Customer customer = dao.getByPK(id).get();
                return customer;
            } else {
                throw new ServiceException("Can`t find customer with id = " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Get by PK customer error");
        }
    }

    @Override
    public void update(Customer customer) throws ServiceException {
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isExist(AppConstant.LOGIN_PARAMENR, Customer.class, customer.getLogin());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.update(customer);

        } catch (DaoException e) {
            throw new ServiceException(e, "Update customer error");
        } catch (ValidationException e) {
            throw new ServiceException("This customer not exist");
        }
    }

    @Override
    public List<Customer> getList() throws ServiceException {
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, "Get customer list error");
        }
    }

    @Override
    public Customer getByEmail(String email) throws ServiceException {
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            return dao.getByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e, "Get customer list error");
        }
    }
}
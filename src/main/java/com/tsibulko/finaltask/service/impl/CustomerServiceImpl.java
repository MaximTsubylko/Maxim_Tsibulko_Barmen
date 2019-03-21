package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.CustomerDAO;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.*;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.EncryptPassword;
import com.tsibulko.finaltask.util.StringGenerator;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();


    public void setNewStateWithKey(Customer customer, String reaquestKey, String name) throws ServiceException {
        Integer userID = customer.getId();
        UserKey userKey = UserKey.getInstance();
        if (reaquestKey.equals(userKey.get(userID))) {
            Customer validCustomer = getByPK(userID);
            validCustomer.setState(UserState.getByName(name).getId());
            update(validCustomer);
        } else {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CHANGE_STATE);
            throw new ServiceException("Validation error");
        }
    }

    public void setNewState(Customer customer, String name) throws ServiceException {
        customer.setState(UserState.getByName(name).getId());
        update(customer);

    }

    public void setNewRole(Customer customer, Integer id) throws ServiceException {
        customer.setRole_id(id);
        update(customer);

    }

    public Customer restorePassword(Customer customer, String reaquestKey) throws ServiceException {
        StringGenerator generator = new StringGenerator();
        String newPassword = generator.generate();
        Integer userID = customer.getId();
        UserKey userKey = UserKey.getInstance();
        if (reaquestKey.equals(userKey.get(userID))) {
            Customer validCustomer = getByPK(userID);
            customer.setPassword(newPassword);
            EncryptPassword.encrypt(customer);
            update(customer);
            return validCustomer;

        } else {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CHANGE_PASSWORD);
            throw new ServiceException("Validation error");
        }
    }

    public Customer changePassword(Customer customer, String newPassword) throws ServiceException {
        customer.setPassword(newPassword);
        EncryptPassword.encrypt(customer);
        update(customer);
        return customer;
    }

    public Customer getCustomerWithCocktails(Integer id, Customer customer) throws ServiceException {
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        customer = getByPK(id);
        customer.setCocktails(cocktailService.getCocktailByCustomer(customer));
        return customer;
    }


    @Override
    public Customer create(Customer customer) throws ServiceException {
        CustomerDAO dao;
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isUnique(new String[]{AppConstant.LOGIN_PARAMENR, AppConstant.EMAIL_PARAMETR},
                    Customer.class, customer.getLogin(), customer.getEmail());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            EncryptPassword.encrypt(customer);
            dao.persist(customer);
            return customer;
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_CUSTOMER);
            throw new ServiceException(e, "Dao error");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_UNIQUE_CUSTOMER);
            throw new ServiceException("Validation error");
        }
    }

    @Override
    public void delete(Customer customer) throws ServiceException {
        CustomerDAO dao;
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isExist(AppConstant.LOGIN_PARAMENR, Customer.class, customer.getLogin());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.delete(customer);

        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_DELETE_CUSTOMER);
            throw new ServiceException(e, "Dao error");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException("Validation error");
        }
    }

    @Override
    public Customer getByPK(Integer id) throws ServiceException {
        CustomerDAO dao;
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (dao.getByPK(id).isPresent()) {
                Customer customer = dao.getByPK(id).get();
                return customer;
            } else {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
                throw new ServiceException("Dao error");
            }
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException(e, "Dao error");
        }
    }

    @Override
    public void update(Customer customer) throws ServiceException {
        CustomerDAO dao;
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isExist(AppConstant.LOGIN_PARAMENR, Customer.class, customer.getLogin());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.update(customer);

        } catch (DaoException e) {
            throw new ServiceException(e, "Dao error");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException("Validation error");
        }
    }

    @Override
    public List<Customer> getList() throws ServiceException {
        CustomerDAO dao;
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, "Dao error");
        }
    }

    @Override
    public Customer getByEmail(String email) throws ServiceException {
        CustomerDAO dao;
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            return dao.getByEmail(email);
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException(e, "Dao error");
        }
    }
}
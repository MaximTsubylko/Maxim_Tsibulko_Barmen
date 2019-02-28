package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.CustomerService;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.impl.LoginAndRegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomerServiceImpl implements CustomerService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CustomerDAO dao;
    private static Map<String,Customer> authenticatedCustomer = new WeakHashMap<>();
    private LoginAndRegistrationValidator validator = (LoginAndRegistrationValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGANDREG);

    private void encryptPassword(Customer user) throws ServiceException {
        try {
            String passwordWithSalt = user.getPassword() + user.getLogin();
            byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            String decimalHash = IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                    .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
            user.setPassword(decimalHash);
        } catch (NoSuchAlgorithmException e){
            throw new ServiceException(e,"Encrypt password error");
        }
    }

    public Customer authenticate(Customer user, HttpSession session) throws ServiceException {
        try {
            dao = (CustomerDAO)daoFactory.getDao(Customer.class);
            if (!dao.getStringsFromColumn("login").contains(user.getLogin())) {
                throw new ServiceException("error");
            }
            encryptPassword(user);
            Customer validUser = dao.findByLogin(user);
            if (!user.getPassword().equals(validUser.getPassword())){
                throw new ServiceException("error");
            }
            session.setAttribute("sessionAttribute",validUser);
            authenticatedCustomer.put(session.getId(),validUser);
            return validUser;
        }catch (DaoException e){
            throw new ServiceException(e,"Authenticate error");
        }
    }

    public static boolean isAuthenticated(HttpSession session){
        if (session.getAttribute("sessionAttribute") == null){
            return false;
        }

        if (authenticatedCustomer.get(session.getId()).equals(session.getAttribute("sessionAttribute"))){
            return true;
        }

        return false;
    }

    public static void logout(HttpSession session){
        session.removeAttribute("sessionAttribute");
        authenticatedCustomer.remove(session.getId());
    }

    @Override
    public Customer create(Customer customer) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isUniqueCustomer(customer)) {
                dao = (CustomerDAO) daoFactory.getDao(Customer.class);
                encryptPassword(customer);
                dao.persist(customer);
                return customer;
            } else {
                throw new ServiceDateValidationException("Not unique user name");
            }
        } catch (DaoException e){
            throw new ServiceException(e,"Create customer error");
        }
    }

    @Override
    public void delete(Customer customer) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isExistCustomer(customer.getLogin())) {
                dao = (CustomerDAO) daoFactory.getDao(Customer.class);
                dao.delete(customer);
            } else {
                throw new ServiceDateValidationException("This customer not exist!");
            }
        } catch (DaoException e){
            throw new ServiceException(e,"delete error");
        }
    }

    @Override
    public Customer getByPK(Integer id) throws ServiceDateValidationException, ServiceException {
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (dao.getByPK(id).isPresent()) {
                Customer customer = (Customer) dao.getByPK(id).get();
                return customer;
            } else {
                throw new ServiceDateValidationException("Can`t find customer with id = " + id);
            }
        } catch (DaoException e){
            throw new ServiceException(e,"Get by PK customer error");
        }
    }

    @Override
    public void update(Customer customer) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isExistCustomer(customer.getLogin())) {
                dao = (CustomerDAO) daoFactory.getDao(Customer.class);
                dao.update(customer);
            } else {
                throw new ServiceDateValidationException("This customer not exist!");
            }
        } catch (DaoException e){
            throw new ServiceException(e,"Update customer error");
        }
    }

    @Override
    public List<Customer> getList() throws ServiceException {
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            return dao.getAll();
        } catch (DaoException e){
            throw new ServiceException(e,"Get customer list error");
        }
    }
}

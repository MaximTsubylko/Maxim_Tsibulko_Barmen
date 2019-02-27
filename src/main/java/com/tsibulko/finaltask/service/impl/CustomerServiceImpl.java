package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.CustomerService;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
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

    private void encryptPassword(Customer user) throws NoSuchAlgorithmException {
        String passwordWithSalt = user.getPassword() + user.getLogin();

        byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));

        String decimalHash = IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());

        user.setPassword(decimalHash);
    }

    public Customer authenticate(Customer user, HttpSession session) throws ServiceException {
        SessionAttribute sessionAttribute = new SessionAttribute();
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
        }catch (DaoException | NoSuchAlgorithmException e){
            throw new ServiceException(e);
        }
    }

    public static boolean isAuthenticated(Object object, HttpSession session){
        if (object == null){
            return false;
        }

        if (authenticatedCustomer.get(session.getId()).equals(object)){
            return true;
        }

        return false;
    }

    @Override
    public Customer create(Customer customer) throws SQLException, DaoException, PersistException, ServiceDateValidationException, NoSuchAlgorithmException {
        if (validator.isUniqueCustomer(customer)) {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            encryptPassword(customer);
            dao.persist(customer);
            return customer;
        } else {
            throw new ServiceDateValidationException("Not unique user name");
        }
    }

    @Override
    public void delete(Customer customer) throws SQLException, PersistException, DaoException, ServiceDateValidationException {
        if (validator.isExistCustomer(customer.getLogin())) {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.delete(customer);
        } else {
            throw new ServiceDateValidationException("This customer not exist!");
        }
    }

    @Override
    public Customer getByPK(Integer id) throws DaoException, SQLException, InterruptedException, ServiceDateValidationException {
        dao = (CustomerDAO) daoFactory.getDao(Customer.class);
        if (dao.getByPK(id).isPresent()) {
            Customer customer = (Customer) dao.getByPK(id).get();
            return customer;
        } else {
            throw new ServiceDateValidationException("Can`t find customer with id = "+id);
        }
    }

    @Override
    public void update(Customer customer) throws DaoException, SQLException, PersistException, ServiceDateValidationException {
        if (validator.isExistCustomer(customer.getLogin())) {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            dao.update(customer);
        } else {
            throw new ServiceDateValidationException("This customer not exist!");
        }
    }

    @Override
    public List<Customer> getList() throws DaoException, SQLException {
        dao = (CustomerDAO) daoFactory.getDao(Customer.class);
        return dao.getAll();
    }
}

package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserRole;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.CustomerService;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.service.message.CustomMessageFactory;
import com.tsibulko.finaltask.service.message.CustomMessageType;
import com.tsibulko.finaltask.validation.CustomerValidator;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomerServiceImpl implements CustomerService {
    private static final String SESSION_ATTRIBUTE = "user";
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CustomerDAO dao;
    private static Map<String, Customer> authenticatedCustomer = new WeakHashMap<>();


    public static void logout(HttpSession session) {
        session.setAttribute(SESSION_ATTRIBUTE, null);
    }

    private void encryptPassword(Customer user) throws ServiceException {
        try {
            String passwordWithSalt = user.getPassword() + user.getLogin();
            byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            String decimalHash = IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                    .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
            user.setPassword(decimalHash);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e, "Encrypt password error");
        }
    }

    public Customer signUp(HttpServletRequest request) throws ServiceException {
        JdbcDaoFactory daoFactory = (JdbcDaoFactory) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        Customer customer = new Customer();
        MailSender sender = new MailSender();
        CustomMessage customMessage = CustomMessageFactory.getInstance().getMessage(CustomMessageType.CONFIRM);
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        customer.setEmail(request.getParameter("email"));
        try {
            CustomerValidator validator = new CustomerValidator();
            validator.doValidation(customer);

            customer.setRole_id(UserRole.CUSTOMER.getId());
            customer.setState(UserState.WAITING_CONFIRMATION.getId());

            GenericDAO<Customer, Integer> userDao = daoFactory.getDao(Customer.class);
            sender.send(request, customMessage);
            encryptPassword(customer);
            return userDao.persist(customer);
        } catch (DaoException e) {
            throw new ServiceException(e, "Failed  with DAO. ");
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
    }

    public Customer logIn(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Customer customer = new Customer();
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (!dao.getStringsFromColumn("login").contains(customer.getLogin())) {
                throw new ServiceException("Not match customer with this login");
            }
            encryptPassword(customer);
            Customer validUser = dao.findByLogin(customer);
            if (!customer.getPassword().equals(validUser.getPassword())) {
                throw new ServiceException("Incorrect password!");
            }
            session.setAttribute(SESSION_ATTRIBUTE, validUser);
            authenticatedCustomer.put(session.getId(), validUser);
            return validUser;
        } catch (DaoException e) {
            throw new ServiceException("Incorrect login or password", e);
        }
    }

    @Override
    public Customer create(Customer customer) throws ServiceException {
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isUnique(new String[]{"login,email"},Customer.class, customer.getLogin(), customer.getEmail());
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            encryptPassword(customer);
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
            fieldValidator.isExist("login", Customer.class, customer.getLogin());
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
            fieldValidator.isExist("login", Customer.class, customer.getLogin());
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
}
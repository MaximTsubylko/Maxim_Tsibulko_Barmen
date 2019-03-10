package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserRole;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.*;
import com.tsibulko.finaltask.util.StringGenerator;
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
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomerServiceImpl implements CustomerService {
    private static final String SESSION_ATTRIBUTE = "user";
    private static final String VALUE_PARAMETR = "value";
    private static final String ID_PARAMETR = "user_id";
    private static final String EMAIL_PARAMETR = "email";
    private static final String LOGIN_PARAMENR = "login";
    private static final String PASSWORD_PARAMETR = "password";
    private static final String FIRST_NAME_PARAMETR = "first_name";
    private static final String SECOND_NAME_PARAMETR = "second_name";
    private static final String NEW_PASSWORD_PARAMETR = "new_password";
    private static final String ACTIV_LINK = "Your activation link: %s/barman?command=activate_user&user_id=%d&value=%s";
    private static final String ACTIV_TITLE = "Activation message from barmen helper!";
    private static final String RESTORE_LINK = "Your restore link: %s/barman?command=change_password&user_id=%d&value=%s";
    private static final String RESTORE_TITLE = "Restore password message from barmen helper!";

    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CustomerDAO dao;
    private static Map<String, Customer> authenticatedCustomer = new WeakHashMap<>();


    public void logout(HttpSession session) {
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
        customer.setLogin(request.getParameter(LOGIN_PARAMENR));
        customer.setPassword(request.getParameter(PASSWORD_PARAMETR));
        customer.setEmail(request.getParameter(EMAIL_PARAMETR));
        try {
            CustomerValidator validator = new CustomerValidator();
            validator.doValidation(customer);

            customer.setRole_id(UserRole.CUSTOMER.getId());
            customer.setState(UserState.WAITING_CONFIRMATION.getId());

            GenericDAO<Customer, Integer> userDao = daoFactory.getDao(Customer.class);
            encryptPassword(customer);
            customer = userDao.persist(customer);
            sendActivationLinkEmail(customer, request, ACTIV_TITLE, ACTIV_LINK);
            return customer;
        } catch (DaoException e) {
            throw new ServiceException(e, "Failed  with DAO. ");
        } catch (ValidationException e) {
            throw new ServiceException(e);
        }
    }

    public Customer logIn(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Customer customer = new Customer();
        customer.setLogin(request.getParameter(LOGIN_PARAMENR));
        customer.setPassword(request.getParameter(PASSWORD_PARAMETR));
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (!dao.findStringsFromColumn(LOGIN_PARAMENR).contains(customer.getLogin())) {
                throw new ServiceException("Not match customer with this login");
            }
            encryptPassword(customer);
            Optional<Customer> validUser = dao.getByLogin(customer.getLogin());
            if (!validUser.isPresent()){
                throw new ServiceException("err");
            }
            if (!customer.getPassword().equals(validUser.get().getPassword())) {
                throw new ServiceException("Incorrect password!");
            }
            session.setAttribute(SESSION_ATTRIBUTE, validUser.get());
            authenticatedCustomer.put(session.getId(), validUser.get());
            return validUser.get();
        } catch (DaoException e) {
            throw new ServiceException("Incorrect login or password", e);
        }
    }


    public String buildActivationLink(String randomString, Integer userId, String url, String link) {
        return String.format(link, url, userId, randomString);
    }


    private void sendActivationLinkEmail(Customer customer, HttpServletRequest request, String title, String link) throws ServiceException {
        StringGenerator generator = new StringGenerator();
        UserKey userKey = UserKey.getInstance();
        String randomString = generator.generate();
        userKey.add(customer.getId(), randomString);
        MailSender sender = MailSender.getInstance();
        Integer port = request.getLocalPort();
        String url;
        url = "http://207.154.220.222" + ":" + port + request.getContextPath();
        String buildLink = buildActivationLink(randomString, customer.getId(), url, link);
        CustomMessage activationMessage = new CustomMessage(title, buildLink);
        sender.send(request, activationMessage);
    }

    public void sendRestoreEmail(HttpServletRequest request) throws ServiceException {
        FieldValidator validator = FieldValidator.getInstance();
        try {
            validator.emailMatches(request.getParameter(EMAIL_PARAMETR));
            validator.isExist(EMAIL_PARAMETR,Customer.class, request.getParameter(EMAIL_PARAMETR));
            String customerEmail = request.getParameter(EMAIL_PARAMETR);
            Customer customer = getByEmail(customerEmail);
            sendActivationLinkEmail(customer, request, RESTORE_TITLE, RESTORE_LINK);
        } catch (ValidationException e) {
            throw new ServiceException("Some error in validation date");
        } catch (DaoException e) {
            throw new ServiceException(e,"");
        }
    }


    public void setNewState(HttpServletRequest request, String name) throws ServiceException {
        Integer userID = Integer.valueOf(request.getParameter(ID_PARAMETR));
        String reaquestKey = request.getParameter(VALUE_PARAMETR);
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
        Customer customer = (Customer) session.getAttribute(SESSION_ATTRIBUTE);
        customer.setFirst_name(request.getParameter(FIRST_NAME_PARAMETR));
        customer.setSecond_name(request.getParameter(SECOND_NAME_PARAMETR));
        customer.setEmail(request.getParameter(EMAIL_PARAMETR));
        update(customer);
        session.setAttribute(SESSION_ATTRIBUTE,getByPK(customer.getId()));
    }

    public void restorePassword(HttpServletRequest request) throws ServiceException {
        StringGenerator generator = new StringGenerator();
        HttpSession session = request.getSession();
        String newPassword = generator.generate();
        Integer userID = Integer.valueOf(request.getParameter(ID_PARAMETR));
        String reaquestKey = request.getParameter(VALUE_PARAMETR);
        UserKey userKey = UserKey.getInstance();
        if (reaquestKey.equals(userKey.get(userID))) {
            Customer customer = getByPK(userID);
            customer.setPassword(newPassword);
            request.setAttribute(NEW_PASSWORD_PARAMETR, newPassword);
            encryptPassword(customer);
            update(customer);
            session.setAttribute(SESSION_ATTRIBUTE, customer);
        } else {
            throw new ServiceException("Error in change password!");
        }
    }


    @Override
    public Customer create(Customer customer) throws ServiceException {
        try {
            FieldValidator fieldValidator = FieldValidator.getInstance();
            fieldValidator.isUnique(new String[]{LOGIN_PARAMENR, EMAIL_PARAMETR}, Customer.class, customer.getLogin(), customer.getEmail());
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
            fieldValidator.isExist(LOGIN_PARAMENR, Customer.class, customer.getLogin());
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
            fieldValidator.isExist(LOGIN_PARAMENR, Customer.class, customer.getLogin());
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
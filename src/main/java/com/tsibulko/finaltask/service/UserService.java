package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.impl.CustomerDAO;

import java.sql.SQLException;

public class UserService implements Service {
    Customer user = new Customer();
    CustomerDAO userDAO = new CustomerDAO();

    public void registerNewUser(String login, String password) throws SQLException {
        user.setLogin(login);
        user.setPassword(password);
        userDAO.persist(user);
    }
//
//    public void logIn(String login, String password) throws SQLException {
//        user = userDAO.getById(login);
//        if (user.getPassword().equals(password)) {
//            System.out.println(true);
//        } else {
//            System.out.println(false);
//        }
//    }
//
//    public void deleteUser(int id) throws SQLException {
//        userDAO.deleteData(id);
//    }


}

package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.CustomMessage;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MailSender {
    private static final String FILD = "email";
    private static final String USERNAME = "barmensupp@gmail.com";
    private static final String PASSWORD = "asdfG3421";
    private static MailSender instance;
    private static Lock lock = new ReentrantLock();


    public static MailSender getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new MailSender();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    private MailSender() {
    }

    public void send(String email, CustomMessage m) throws ServiceException {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        FieldValidator validator = FieldValidator.getInstance();
        String to = email;

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            validator.isExist(FILD, Customer.class, email);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(m.getSubject());
            message.setText(m.getText());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException(e, "DAO error");
        }
    }
}

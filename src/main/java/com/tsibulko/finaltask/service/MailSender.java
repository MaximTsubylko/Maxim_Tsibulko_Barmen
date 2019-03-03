package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class MailSender {
    private static final String FILD = "email";
    private static final String USERNAME = "barmensupp@gmail.com";
    private static final String PASSWORD = "asdfG3421";

    public void send(HttpServletRequest request, CustomMessage m) throws ServiceException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        FieldValidator validator = FieldValidator.getInstance();
        String to = request.getParameter("email");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            validator.isExist(FILD, request.getParameter(FILD));

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
            throw new ServiceException(e, "Not exist email!");
        }
    }
}

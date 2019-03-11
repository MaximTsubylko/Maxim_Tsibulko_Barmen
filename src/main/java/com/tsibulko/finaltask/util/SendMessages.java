package com.tsibulko.finaltask.util;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.service.*;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.service.impl.MailSender;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.StringGenerator;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;

public class SendMessages {


    public String buildActivationLink(String randomString, Integer userId, String url, String link) {
        return String.format(link, url, userId, randomString);
    }


    public void sendActivationLinkEmail(Customer customer, HttpServletRequest request, String title, String link) throws ServiceException {
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
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        try {
            validator.emailMatches(request.getParameter(AppConstant.EMAIL_PARAMETR));
            validator.isExist(AppConstant.EMAIL_PARAMETR,Customer.class, request.getParameter(AppConstant.EMAIL_PARAMETR));
            String customerEmail = request.getParameter(AppConstant.EMAIL_PARAMETR);
            Customer customer = service.getByEmail(customerEmail);
            sendActivationLinkEmail(customer, request, AppConstant.RESTORE_TITLE, AppConstant.RESTORE_LINK);
        } catch (ValidationException e) {
            throw new ServiceException("Some error in validation date");
        } catch (DaoException e) {
            throw new ServiceException(e,"");
        }
    }

}

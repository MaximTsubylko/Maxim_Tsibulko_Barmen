package com.tsibulko.finaltask.util;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.*;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.service.impl.MailSender;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

public class SendMessages {


    public String buildActivationLink(String randomString, Integer userId, String url, String link) {
        return String.format(link, url, userId, randomString);
    }


    public void sendActivationLinkEmail(Customer customer, Integer port, String contextPath, String title, String link) throws ServiceException {
        StringGenerator generator = new StringGenerator();
        UserKey userKey = UserKey.getInstance();
        String randomString = generator.generate();
        userKey.add(customer.getId(), randomString);
        MailSender sender = MailSender.getInstance();
        String url;
        url = "http://207.154.220.222" + ":" + port + contextPath;
        String buildLink = buildActivationLink(randomString, customer.getId(), url, link);
        CustomMessage activationMessage = new CustomMessage(title, buildLink);

        sender.send(customer.getEmail(), activationMessage);
    }

    public void sendRestoreEmail(Customer customer, Integer port, String contextPath) throws ServiceException {
        FieldValidator validator = FieldValidator.getInstance();
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        try {
//            validator.emailMatches(customer.getEmail());
            validator.isExist(AppConstant.EMAIL_PARAMETR, Customer.class, customer.getEmail());
            String customerEmail = customer.getEmail();
            Customer validCustomer = service.getByEmail(customerEmail);
            sendActivationLinkEmail(validCustomer, port, contextPath, AppConstant.RESTORE_TITLE, AppConstant.RESTORE_LINK);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_EMAIL);
            throw new ServiceException("");
        } catch (DaoException e) {
            throw new ServiceException(e, "");
        }
    }

}

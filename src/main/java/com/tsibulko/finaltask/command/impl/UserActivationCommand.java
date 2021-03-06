package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserActivationCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        String reaquestKey = request.getParameter(AppConstant.VALUE_PARAMETR);
        Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));

        Customer customer = service.getByPK(id);
        service.setNewStateWithKey(customer, reaquestKey, AppConstant.ACTIVE_STATE);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.ACTIVATE_USER.getRout(), Router.Type.FORWARD));
        return responseContent;
    }
}

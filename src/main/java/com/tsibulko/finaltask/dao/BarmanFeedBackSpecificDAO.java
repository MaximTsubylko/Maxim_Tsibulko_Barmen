package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.Customer;

import java.util.List;

public interface BarmanFeedBackSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {

    List<BarmenFeedback> getCocktailFeedbackByCustomer(Customer customer) throws DaoException;
}

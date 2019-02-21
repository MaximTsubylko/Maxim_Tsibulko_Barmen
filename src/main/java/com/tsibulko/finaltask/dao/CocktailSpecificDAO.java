package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CocktailSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    List<T> getCocktailByCustomer(Customer customer) throws SQLException;
}

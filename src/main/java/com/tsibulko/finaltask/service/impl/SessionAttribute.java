package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;

public class SessionAttribute {
    private String name;
    private Customer value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Customer getValue() {
        return value;
    }
    public void setValue(Customer value) {
        this.value = value;
    }
}

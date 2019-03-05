package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public enum UserState implements Identified<Integer> {
    ACTIVE(1,"active"),
    BLOCKED(2,"bloked"),
    WAITING_CONFIRMATION(3,"waiting_confirmation");

    private String name;
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    public static UserState getByName(String name) {
        return UserState.valueOf(name.toUpperCase());
    }


    @Override
    public void setId(Integer object) {
        throw new UnsupportedOperationException();
    }

    UserState(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

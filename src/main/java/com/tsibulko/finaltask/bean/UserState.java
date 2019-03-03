package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public enum UserState implements Identified<Integer> {
    ACTIVE(1),
    BLOCKED(2),
    WAITING_CONFIRMATION(3);

    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer object) {
        throw new UnsupportedOperationException();
    }

    UserState(int id){
        this.id = id;
    }
}

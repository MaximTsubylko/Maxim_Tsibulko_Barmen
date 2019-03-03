package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public enum UserRole implements Identified<Integer> {
    ADMINISTRATOR(1,"Administrator"),
    COURIER(2,"Barman"),
    CLIENT(3,"Customer");
    private Integer id;
    private  String role;

    UserRole(int id, String role){
        this.id = id;
        this.role = role;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer anInt) {

    }
}

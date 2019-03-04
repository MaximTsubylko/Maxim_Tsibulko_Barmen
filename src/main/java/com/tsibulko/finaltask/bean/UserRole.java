package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

public enum UserRole implements Identified<Integer> {
    ADMINISTRATOR(3, "Administrator"),
    BARMAN(2, "Barman"),
    CUSTOMER(1, "Customer");
    private Integer id;
    private String role;

    UserRole(int id, String role) {
        this.id = id;
        this.role = role;

    }


    public static UserRole getRoleById(Integer id) {
        switch (id) {
            default:
                return UserRole.CUSTOMER;
            case 1:
                return UserRole.CUSTOMER;
            case 2:
                return UserRole.BARMAN;
            case 3:
                return UserRole.ADMINISTRATOR;
        }
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer anInt) {

    }
}

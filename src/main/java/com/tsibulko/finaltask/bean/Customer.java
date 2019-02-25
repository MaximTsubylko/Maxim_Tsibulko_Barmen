package com.tsibulko.finaltask.bean;

import com.tsibulko.finaltask.dao.Identified;

import java.sql.Date;
import java.util.List;

public class Customer implements Identified<Integer> {
    private Integer id;
    private String first_name;
    private String second_name;
    private String login;
    private String password;
    private Date registr_date;
    private int state = 3;
    private String email;
    private List<Cocktail> cocktails;
    private int role_id = 1;

    public Customer() {
    }

    public Customer(Integer id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Customer(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistr_date() {
        return registr_date;
    }

    public void setRegistr_date(Date registr_date) {
        this.registr_date = registr_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (state != customer.state) return false;
        if (role_id != customer.role_id) return false;
        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (first_name != null ? !first_name.equals(customer.first_name) : customer.first_name != null) return false;
        if (second_name != null ? !second_name.equals(customer.second_name) : customer.second_name != null)
            return false;
        if (login != null ? !login.equals(customer.login) : customer.login != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (registr_date != null ? !registr_date.equals(customer.registr_date) : customer.registr_date != null)
            return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        return cocktails != null ? cocktails.equals(customer.cocktails) : customer.cocktails == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (second_name != null ? second_name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registr_date != null ? registr_date.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cocktails != null ? cocktails.hashCode() : 0);
        result = 31 * result + role_id;
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", registr_date=" + registr_date +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", cocktails=" + cocktails +
                ", role_id=" + role_id +
                '}';
    }
}

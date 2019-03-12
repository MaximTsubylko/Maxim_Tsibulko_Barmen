package com.tsibulko.finaltask.dto;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.UserRole;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.Identified;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class UserDto implements Identified<Integer>, Serializable {
    private Integer id;
    private String first_name;
    private String second_name;
    private String login;
    private String password;
    private String email;
    private double averageMark;
    private List<Cocktail> cocktails;
    private UserRole role_id;
    private UserState state;
    private Date registr_date;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public UserRole getRole_id() {
        return role_id;
    }

    public void setRole_id(UserRole role_id) {
        this.role_id = role_id;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public Date getRegistr_date() {
        return registr_date;
    }

    public void setRegistr_date(Date registr_date) {
        this.registr_date = registr_date;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer anInt) {

    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", averageMark=" + averageMark +
                ", cocktails=" + cocktails +
                ", role_id=" + role_id +
                ", state=" + state +
                ", registr_date=" + registr_date +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (Double.compare(userDto.averageMark, averageMark) != 0) return false;
        if (id != null ? !id.equals(userDto.id) : userDto.id != null) return false;
        if (first_name != null ? !first_name.equals(userDto.first_name) : userDto.first_name != null) return false;
        if (second_name != null ? !second_name.equals(userDto.second_name) : userDto.second_name != null) return false;
        if (login != null ? !login.equals(userDto.login) : userDto.login != null) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        if (email != null ? !email.equals(userDto.email) : userDto.email != null) return false;
        if (cocktails != null ? !cocktails.equals(userDto.cocktails) : userDto.cocktails != null) return false;
        if (role_id != userDto.role_id) return false;
        if (state != userDto.state) return false;
        return registr_date != null ? registr_date.equals(userDto.registr_date) : userDto.registr_date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (second_name != null ? second_name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        temp = Double.doubleToLongBits(averageMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cocktails != null ? cocktails.hashCode() : 0);
        result = 31 * result + (role_id != null ? role_id.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (registr_date != null ? registr_date.hashCode() : 0);
        return result;
    }
}

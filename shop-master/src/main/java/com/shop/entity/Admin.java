package com.shop.entity;

import java.io.Serializable;

/**
 * 管理员实体类
 * 
 * @author shop
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer aid;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(Integer aid, String username, String password) {
        this.aid = aid;
        this.username = username;
        this.password = password;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", username='" + username + '\'' +
                '}';
    }
}

package cn.edu.nuc.shop.entity;

import java.io.Serializable;

/**
 * 管理员实体类
 * 对应数据库表：zk_admin
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 管理员ID */
    private Integer aid;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /**
     * 无参构造方法
     */
    public Admin() {
        super();
    }

    /**
     * 全参构造方法
     */
    public Admin(Integer aid, String username, String password) {
        super();
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
                ", password='" + password + '\'' +
                '}';
    }
}

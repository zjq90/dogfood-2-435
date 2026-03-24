package com.example.shop.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 管理员实体类
 * 对应数据库表: zk_admin
 *
 * @author example
 * @version 1.0.0
 */
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    private Integer adminId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20个字符之间")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码长度必须在4-32个字符之间")
    private String password;

    public Admin() {
    }

    public Admin(Integer adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }
}

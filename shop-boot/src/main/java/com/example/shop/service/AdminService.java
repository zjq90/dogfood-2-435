package com.example.shop.service;

import com.example.shop.entity.Admin;

/**
 * 管理员服务接口
 *
 * @author example
 * @version 1.0.0
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员信息
     * @throws RuntimeException 登录失败时抛出异常
     */
    Admin login(String username, String password);

    /**
     * 根据ID查询管理员
     *
     * @param adminId 管理员ID
     * @return 管理员信息
     */
    Admin findById(Integer adminId);

    /**
     * 修改管理员密码
     *
     * @param adminId     管理员ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改成功返回true
     */
    boolean changePassword(Integer adminId, String oldPassword, String newPassword);
}

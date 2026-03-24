package com.example.shop.service;

import com.example.shop.entity.User;

/**
 * 用户服务接口
 *
 * @author example
 * @version 1.0.0
 */
public interface UserService {

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return true:已存在 false:不存在
     */
    boolean checkUsernameExists(String username);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     * @throws RuntimeException 登录失败时抛出异常
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册成功返回true
     */
    boolean register(User user);

    /**
     * 根据ID查询用户
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    User findById(Integer userId);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新成功返回true
     */
    boolean updateUser(User user);
}

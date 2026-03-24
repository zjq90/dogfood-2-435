package com.shop.service;

import com.shop.entity.User;

/**
 * 用户服务接口
 * 
 * @author shop
 */
public interface UserService {

    int checkUsername(String username);

    User login(String username, String password);
}

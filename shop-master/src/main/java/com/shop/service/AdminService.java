package com.shop.service;

import com.shop.entity.Admin;

/**
 * 管理员服务接口
 * 
 * @author shop
 */
public interface AdminService {

    Admin login(String username, String password);
}

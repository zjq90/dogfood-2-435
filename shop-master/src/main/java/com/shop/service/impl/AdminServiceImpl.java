package com.shop.service.impl;

import com.shop.entity.Admin;
import com.shop.mapper.AdminMapper;
import com.shop.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员服务实现类
 * 
 * @author shop
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        logger.debug("管理员登录: {}", username);
        Admin admin = adminMapper.findByUsernameAndPassword(username, password);
        if (admin == null) {
            logger.warn("管理员登录失败，用户名或密码错误: {}", username);
            throw new RuntimeException("用户名或密码不正确");
        }
        logger.info("管理员登录成功: {}", username);
        return admin;
    }
}

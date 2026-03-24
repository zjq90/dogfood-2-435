package com.example.shop.service.impl;

import com.example.shop.entity.Admin;
import com.example.shop.mapper.AdminMapper;
import com.example.shop.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员服务实现类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    @Transactional(readOnly = true)
    public Admin login(String username, String password) {
        log.info("管理员登录: {}", username);
        Admin admin = adminMapper.findByUsernameAndPassword(username, password);
        if (admin == null) {
            log.warn("管理员登录失败,用户名或密码错误: {}", username);
            throw new RuntimeException("用户名或密码不正确");
        }
        log.info("管理员登录成功: {}", username);
        return admin;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findById(Integer adminId) {
        log.debug("查询管理员, adminId={}", adminId);
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public boolean changePassword(Integer adminId, String oldPassword, String newPassword) {
        log.info("管理员修改密码, adminId={}", adminId);

        // 验证旧密码
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        if (admin == null) {
            log.warn("修改密码失败,管理员不存在, adminId={}", adminId);
            throw new RuntimeException("管理员不存在");
        }

        if (!admin.getPassword().equals(oldPassword)) {
            log.warn("修改密码失败,旧密码错误, adminId={}", adminId);
            throw new RuntimeException("旧密码不正确");
        }

        // 更新密码
        admin.setPassword(newPassword);
        int result = adminMapper.updateByPrimaryKeySelective(admin);
        if (result > 0) {
            log.info("修改密码成功, adminId={}", adminId);
            return true;
        }
        log.error("修改密码失败, adminId={}", adminId);
        return false;
    }
}

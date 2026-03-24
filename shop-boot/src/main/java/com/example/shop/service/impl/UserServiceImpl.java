package com.example.shop.service.impl;

import com.example.shop.entity.User;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public boolean checkUsernameExists(String username) {
        log.debug("检查用户名是否存在: {}", username);
        int count = userMapper.checkUsernameExists(username);
        log.debug("用户名[{}]存在数量: {}", username, count);
        return count > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public User login(String username, String password) {
        log.info("用户登录: {}", username);
        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            log.warn("用户登录失败,用户名或密码错误: {}", username);
            throw new RuntimeException("用户名或密码不正确");
        }
        log.info("用户登录成功: {}", username);
        return user;
    }

    @Override
    public boolean register(User user) {
        log.info("用户注册: {}", user.getUsername());

        // 检查用户名是否已存在
        if (checkUsernameExists(user.getUsername())) {
            log.warn("用户注册失败,用户名已存在: {}", user.getUsername());
            throw new RuntimeException("用户名已存在");
        }

        int result = userMapper.insertSelective(user);
        if (result > 0) {
            log.info("用户注册成功: {}, userId={}", user.getUsername(), user.getUserId());
            return true;
        }
        log.error("用户注册失败: {}", user.getUsername());
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer userId) {
        log.debug("查询用户, userId={}", userId);
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean updateUser(User user) {
        log.info("更新用户信息, userId={}", user.getUserId());
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            log.info("更新用户信息成功, userId={}", user.getUserId());
            return true;
        }
        log.error("更新用户信息失败, userId={}", user.getUserId());
        return false;
    }
}

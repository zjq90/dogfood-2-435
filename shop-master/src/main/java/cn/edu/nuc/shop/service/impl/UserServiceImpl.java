package cn.edu.nuc.shop.service.impl;

import cn.edu.nuc.shop.entity.User;
import cn.edu.nuc.shop.mapper.UserMapper;
import cn.edu.nuc.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类
 * 实现用户相关业务逻辑
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User login(String username, String password) {
        logger.info("用户登录验证，用户名: {}", username);
        User user = userMapper.findByUser(username, password);
        if (user == null) {
            logger.warn("用户登录失败，用户名或密码错误: {}", username);
            throw new RuntimeException("用户名或密码错误");
        }
        logger.info("用户登录成功，用户名: {}", username);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkUsername(String username) {
        logger.debug("检查用户名是否存在: {}", username);
        int count = userMapper.checkUsername(username);
        boolean exists = count > 0;
        logger.debug("用户名 {} {}", username, exists ? "已存在" : "不存在");
        return exists;
    }

    @Override
    public int register(User user) {
        logger.info("用户注册: {}", user.getUsername());
        // 检查用户名是否已存在
        if (checkUsername(user.getUsername())) {
            logger.warn("用户注册失败，用户名已存在: {}", user.getUsername());
            throw new RuntimeException("用户名已存在");
        }
        int result = userMapper.insertSelective(user);
        logger.info("用户注册成功: {}", user.getUsername());
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer uid) {
        logger.debug("根据ID查询用户: {}", uid);
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        logger.debug("查询所有用户");
        return userMapper.selectAllUsers();
    }

    @Override
    public int update(User user) {
        logger.info("更新用户信息: {}", user.getUid());
        int result = userMapper.updateByPrimaryKeySelective(user);
        logger.info("更新用户信息成功: {}", user.getUid());
        return result;
    }

    @Override
    public int delete(Integer uid) {
        logger.info("删除用户: {}", uid);
        int result = userMapper.deleteByPrimaryKey(uid);
        logger.info("删除用户成功: {}", uid);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByUsernameLike(String username) {
        logger.debug("根据用户名模糊查询: {}", username);
        return userMapper.selectByUsernameLike(username);
    }
}

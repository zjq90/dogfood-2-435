package cn.edu.nuc.shop.service.impl;

import cn.edu.nuc.shop.entity.Admin;
import cn.edu.nuc.shop.mapper.AdminMapper;
import cn.edu.nuc.shop.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员服务实现类
 * 实现管理员相关业务逻辑
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(readOnly = true)
    public Admin login(String username, String password) {
        logger.info("管理员登录验证，用户名: {}", username);
        Admin admin = adminMapper.login(username, password);
        if (admin == null) {
            logger.warn("管理员登录失败，用户名或密码错误: {}", username);
            throw new RuntimeException("用户名或密码错误");
        }
        logger.info("管理员登录成功: {}", username);
        return admin;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findById(Integer aid) {
        logger.debug("根据ID查询管理员: {}", aid);
        return adminMapper.selectByPrimaryKey(aid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> findAll() {
        logger.debug("查询所有管理员");
        return adminMapper.selectAllAdmins();
    }

    @Override
    public int addAdmin(Admin admin) {
        logger.info("添加管理员: {}", admin.getUsername());
        // 检查用户名是否已存在
        Admin existAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (existAdmin != null) {
            logger.warn("添加管理员失败，用户名已存在: {}", admin.getUsername());
            throw new RuntimeException("用户名已存在");
        }
        int result = adminMapper.insertSelective(admin);
        logger.info("添加管理员成功: {}", admin.getUsername());
        return result;
    }

    @Override
    public int update(Admin admin) {
        logger.info("更新管理员信息: {}", admin.getAid());
        int result = adminMapper.updateByPrimaryKeySelective(admin);
        logger.info("更新管理员信息成功: {}", admin.getAid());
        return result;
    }

    @Override
    public int delete(Integer aid) {
        logger.info("删除管理员: {}", aid);
        int result = adminMapper.deleteByPrimaryKey(aid);
        logger.info("删除管理员成功: {}", aid);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Admin findByUsername(String username) {
        logger.debug("根据用户名查询管理员: {}", username);
        return adminMapper.selectByUsername(username);
    }
}

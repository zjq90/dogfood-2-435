package cn.edu.nuc.shop.service;

import cn.edu.nuc.shop.entity.Admin;

import java.util.List;

/**
 * 管理员服务接口
 * 定义管理员相关业务逻辑接口
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象
     */
    Admin login(String username, String password);

    /**
     * 根据ID查询管理员
     * @param aid 管理员ID
     * @return 管理员对象
     */
    Admin findById(Integer aid);

    /**
     * 查询所有管理员
     * @return 管理员列表
     */
    List<Admin> findAll();

    /**
     * 添加管理员
     * @param admin 管理员对象
     * @return 影响行数
     */
    int addAdmin(Admin admin);

    /**
     * 更新管理员信息
     * @param admin 管理员对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 删除管理员
     * @param aid 管理员ID
     * @return 影响行数
     */
    int delete(Integer aid);

    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员对象
     */
    Admin findByUsername(String username);
}

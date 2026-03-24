package cn.edu.nuc.shop.service;

import cn.edu.nuc.shop.entity.User;

import java.util.List;

/**
 * 用户服务接口
 * 定义用户相关业务逻辑接口
 */
public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    User login(String username, String password);

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 存在返回true，否则返回false
     */
    boolean checkUsername(String username);

    /**
     * 用户注册
     * @param user 用户对象
     * @return 影响行数
     */
    int register(User user);

    /**
     * 根据ID查询用户
     * @param uid 用户ID
     * @return 用户对象
     */
    User findById(Integer uid);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 影响行数
     */
    int delete(Integer uid);

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @return 用户列表
     */
    List<User> findByUsernameLike(String username);
}

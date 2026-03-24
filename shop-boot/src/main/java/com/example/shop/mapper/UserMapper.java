package com.example.shop.mapper;

import com.example.shop.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问层
 *
 * @author example
 * @version 1.0.0
 */
@Repository
public interface UserMapper {

    /**
     * 根据主键删除用户
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入用户(全字段)
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 插入用户(选择性)
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int insertSelective(User user);

    /**
     * 根据主键查询用户
     *
     * @param userId 用户ID
     * @return 用户实体
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 根据主键更新用户(选择性)
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 根据主键更新用户(全字段)
     *
     * @param user 用户实体
     * @return 影响行数
     */
    int updateByPrimaryKey(User user);

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return 存在返回1,不存在返回0
     */
    int checkUsernameExists(@Param("username") String username);

    /**
     * 用户登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息,验证失败返回null
     */
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    User findByUsername(@Param("username") String username);
}

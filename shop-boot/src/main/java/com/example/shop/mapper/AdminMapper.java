package com.example.shop.mapper;

import com.example.shop.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员数据访问层
 *
 * @author example
 * @version 1.0.0
 */
@Repository
public interface AdminMapper {

    /**
     * 根据主键删除管理员
     *
     * @param adminId 管理员ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer adminId);

    /**
     * 插入管理员(全字段)
     *
     * @param admin 管理员实体
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 插入管理员(选择性)
     *
     * @param admin 管理员实体
     * @return 影响行数
     */
    int insertSelective(Admin admin);

    /**
     * 根据主键查询管理员
     *
     * @param adminId 管理员ID
     * @return 管理员实体
     */
    Admin selectByPrimaryKey(Integer adminId);

    /**
     * 根据主键更新管理员(选择性)
     *
     * @param admin 管理员实体
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Admin admin);

    /**
     * 根据主键更新管理员(全字段)
     *
     * @param admin 管理员实体
     * @return 影响行数
     */
    int updateByPrimaryKey(Admin admin);

    /**
     * 管理员登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员信息,验证失败返回null
     */
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询管理员
     *
     * @param username 用户名
     * @return 管理员实体
     */
    Admin findByUsername(@Param("username") String username);
}

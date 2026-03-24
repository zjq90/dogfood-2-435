package com.shop.mapper;

import com.shop.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员数据访问层
 * 
 * @author shop
 */
public interface AdminMapper {

    int deleteByPrimaryKey(Integer aid);

    int insert(Admin admin);

    int insertSelective(Admin admin);

    Admin selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(Admin admin);

    int updateByPrimaryKey(Admin admin);

    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

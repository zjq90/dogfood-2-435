package com.shop.mapper;

import com.shop.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户数据访问层
 * 
 * @author shop
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer uid);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int checkUsername(String username);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

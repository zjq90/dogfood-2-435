package cn.edu.nuc.shop.mapper;

import cn.edu.nuc.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 * 提供用户相关的数据库操作
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键删除用户
     * @param uid 用户ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer uid);

    /**
     * 插入用户（全字段）
     * @param record 用户对象
     * @return 影响行数
     */
    int insert(User record);

    /**
     * 插入用户（可选字段）
     * @param record 用户对象
     * @return 影响行数
     */
    int insertSelective(User record);

    /**
     * 根据主键查询用户
     * @param uid 用户ID
     * @return 用户对象
     */
    User selectByPrimaryKey(Integer uid);

    /**
     * 根据主键更新用户（可选字段）
     * @param record 用户对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新用户（全字段）
     * @param record 用户对象
     * @return 影响行数
     */
    int updateByPrimaryKey(User record);

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 存在的用户数量
     */
    int checkUsername(String username);

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    User findByUser(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAllUsers();

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @return 用户列表
     */
    List<User> selectByUsernameLike(String username);
}

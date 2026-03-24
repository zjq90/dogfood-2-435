package cn.edu.nuc.shop.mapper;

import cn.edu.nuc.shop.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员Mapper接口
 * 提供管理员相关的数据库操作
 */
@Mapper
public interface AdminMapper {

    /**
     * 根据主键删除管理员
     * @param aid 管理员ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer aid);

    /**
     * 插入管理员（全字段）
     * @param record 管理员对象
     * @return 影响行数
     */
    int insert(Admin record);

    /**
     * 插入管理员（可选字段）
     * @param record 管理员对象
     * @return 影响行数
     */
    int insertSelective(Admin record);

    /**
     * 根据主键查询管理员
     * @param aid 管理员ID
     * @return 管理员对象
     */
    Admin selectByPrimaryKey(Integer aid);

    /**
     * 根据主键更新管理员（可选字段）
     * @param record 管理员对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * 根据主键更新管理员（全字段）
     * @param record 管理员对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Admin record);

    /**
     * 管理员登录验证
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象
     */
    Admin login(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有管理员
     * @return 管理员列表
     */
    List<Admin> selectAllAdmins();

    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员对象
     */
    Admin selectByUsername(String username);
}

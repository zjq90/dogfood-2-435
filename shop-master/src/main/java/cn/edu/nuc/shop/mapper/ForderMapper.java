package cn.edu.nuc.shop.mapper;

import cn.edu.nuc.shop.entity.Forder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper接口
 * 提供订单相关的数据库操作
 */
@Mapper
public interface ForderMapper {

    /**
     * 根据主键删除订单
     * @param fid 订单ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer fid);

    /**
     * 插入订单（全字段）
     * @param record 订单对象
     * @return 影响行数
     */
    int insert(Forder record);

    /**
     * 插入订单（可选字段）- 并返回生成的主键
     * @param record 订单对象
     * @return 影响行数
     */
    int insertSelective(Forder record);

    /**
     * 根据主键查询订单
     * @param fid 订单ID
     * @return 订单对象
     */
    Forder selectByPrimaryKey(Integer fid);

    /**
     * 根据主键更新订单（可选字段）
     * @param record 订单对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Forder record);

    /**
     * 根据主键更新订单（全字段）
     * @param record 订单对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Forder record);

    /**
     * 根据用户ID查询订单列表
     * @param uid 用户ID
     * @return 订单列表
     */
    List<Forder> selectByUid(Integer uid);

    /**
     * 查询所有订单
     * @return 订单列表
     */
    List<Forder> selectAllForders();

    /**
     * 根据订单状态查询订单
     * @param status 订单状态
     * @return 订单列表
     */
    List<Forder> selectByStatus(Integer status);

    /**
     * 分页查询订单
     * @param start 起始位置
     * @param pageSize 每页数量
     * @return 订单列表
     */
    List<Forder> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 查询订单总数
     * @return 订单总数
     */
    int selectCount();

    /**
     * 更新订单状态
     * @param fid 订单ID
     * @param status 新状态
     * @return 影响行数
     */
    int updateStatus(@Param("fid") Integer fid, @Param("status") int status);

    /**
     * 根据用户ID和状态查询订单
     * @param uid 用户ID
     * @param status 订单状态
     * @return 订单列表
     */
    List<Forder> selectByUidAndStatus(@Param("uid") Integer uid, @Param("status") Integer status);
}

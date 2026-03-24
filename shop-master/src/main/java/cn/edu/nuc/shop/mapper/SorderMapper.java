package cn.edu.nuc.shop.mapper;

import cn.edu.nuc.shop.entity.Sorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细Mapper接口
 * 提供订单明细相关的数据库操作
 */
@Mapper
public interface SorderMapper {

    /**
     * 根据主键删除订单明细
     * @param sid 明细ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer sid);

    /**
     * 插入订单明细（全字段）
     * @param record 订单明细对象
     * @return 影响行数
     */
    int insert(Sorder record);

    /**
     * 插入订单明细（可选字段）
     * @param record 订单明细对象
     * @return 影响行数
     */
    int insertSelective(Sorder record);

    /**
     * 根据主键查询订单明细
     * @param sid 明细ID
     * @return 订单明细对象
     */
    Sorder selectByPrimaryKey(Integer sid);

    /**
     * 根据主键更新订单明细（可选字段）
     * @param record 订单明细对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Sorder record);

    /**
     * 根据主键更新订单明细（全字段）
     * @param record 订单明细对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Sorder record);

    /**
     * 根据订单ID查询订单明细
     * @param fid 订单ID
     * @return 订单明细列表
     */
    List<Sorder> selectByFid(Integer fid);

    /**
     * 根据订单ID删除订单明细
     * @param fid 订单ID
     * @return 影响行数
     */
    int deleteByFid(Integer fid);

    /**
     * 批量插入订单明细
     * @param sorderList 订单明细列表
     * @return 影响行数
     */
    int batchInsert(@Param("sorderList") List<Sorder> sorderList);

    /**
     * 查询所有订单明细
     * @return 订单明细列表
     */
    List<Sorder> selectAllSorders();

    /**
     * 根据商品ID查询销售数量
     * @param pid 商品ID
     * @return 销售总数
     */
    int selectSalesCountByPid(Integer pid);
}

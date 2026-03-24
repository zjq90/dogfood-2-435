package com.example.shop.mapper;

import com.example.shop.entity.Forder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单数据访问层
 *
 * @author example
 * @version 1.0.0
 */
@Repository
public interface ForderMapper {

    /**
     * 根据主键删除订单
     *
     * @param orderId 订单ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     * 插入订单(全字段)
     *
     * @param forder 订单实体
     * @return 影响行数
     */
    int insert(Forder forder);

    /**
     * 插入订单(选择性)
     *
     * @param forder 订单实体
     * @return 影响行数
     */
    int insertSelective(Forder forder);

    /**
     * 根据主键查询订单
     *
     * @param orderId 订单ID
     * @return 订单实体
     */
    Forder selectByPrimaryKey(Integer orderId);

    /**
     * 根据主键更新订单(选择性)
     *
     * @param forder 订单实体
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Forder forder);

    /**
     * 根据主键更新订单(全字段)
     *
     * @param forder 订单实体
     * @return 影响行数
     */
    int updateByPrimaryKey(Forder forder);

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    List<Forder> selectAll();

    /**
     * 根据用户ID查询订单
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Forder> selectByUserId(Integer userId);

    /**
     * 更新订单状态为已发货
     *
     * @param orderId 订单ID
     * @return 影响行数
     */
    int updateStatusToShipped(Integer orderId);
}

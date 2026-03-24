package com.example.shop.mapper;

import com.example.shop.entity.Sorder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单项数据访问层
 *
 * @author example
 * @version 1.0.0
 */
@Repository
public interface SorderMapper {

    /**
     * 根据主键删除订单项
     *
     * @param itemId 订单项ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * 插入订单项(全字段)
     *
     * @param sorder 订单项实体
     * @return 影响行数
     */
    int insert(Sorder sorder);

    /**
     * 插入订单项(选择性)
     *
     * @param sorder 订单项实体
     * @return 影响行数
     */
    int insertSelective(Sorder sorder);

    /**
     * 根据主键查询订单项
     *
     * @param itemId 订单项ID
     * @return 订单项实体
     */
    Sorder selectByPrimaryKey(Integer itemId);

    /**
     * 根据主键更新订单项(选择性)
     *
     * @param sorder 订单项实体
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Sorder sorder);

    /**
     * 根据主键更新订单项(全字段)
     *
     * @param sorder 订单项实体
     * @return 影响行数
     */
    int updateByPrimaryKey(Sorder sorder);

    /**
     * 根据订单ID查询订单项列表
     *
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<Sorder> selectByOrderId(Integer orderId);

    /**
     * 根据订单ID删除订单项
     *
     * @param orderId 订单ID
     * @return 影响行数
     */
    int deleteByOrderId(Integer orderId);

    /**
     * 批量插入订单项
     *
     * @param orderItems 订单项列表
     * @return 影响行数
     */
    int batchInsert(List<Sorder> orderItems);
}

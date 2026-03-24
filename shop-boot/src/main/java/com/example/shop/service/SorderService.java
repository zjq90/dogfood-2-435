package com.example.shop.service;

import com.example.shop.entity.Sorder;

import java.util.List;

/**
 * 订单项服务接口
 *
 * @author example
 * @version 1.0.0
 */
public interface SorderService {

    /**
     * 根据订单ID查询订单项列表
     *
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<Sorder> listByOrderId(Integer orderId);

    /**
     * 根据ID查询订单项
     *
     * @param itemId 订单项ID
     * @return 订单项信息
     */
    Sorder findById(Integer itemId);

    /**
     * 删除订单项
     *
     * @param itemId 订单项ID
     * @return 删除成功返回true
     */
    boolean deleteItem(Integer itemId);
}

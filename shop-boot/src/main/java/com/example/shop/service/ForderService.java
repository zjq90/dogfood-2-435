package com.example.shop.service;

import com.example.shop.entity.Forder;
import com.example.shop.entity.Product;

import java.util.List;

/**
 * 订单服务接口
 *
 * @author example
 * @version 1.0.0
 */
public interface ForderService {

    /**
     * 计算购物车总金额
     *
     * @param forder 购物车订单
     * @return 总金额
     */
    Double calculateTotal(Forder forder);

    /**
     * 提交订单
     *
     * @param forder       订单信息
     * @param sessionForder 购物车中的订单
     * @return 提交成功返回true
     * @throws Exception 提交失败时抛出异常
     */
    boolean submitOrder(Forder forder, Forder sessionForder) throws Exception;

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    List<Forder> listAllOrders();

    /**
     * 根据ID查询订单
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    Forder findById(Integer orderId);

    /**
     * 根据用户ID查询订单
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Forder> listByUserId(Integer userId);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 删除成功返回true
     */
    boolean deleteOrder(Integer orderId);

    /**
     * 更新订单状态为已发货
     *
     * @param orderId 订单ID
     * @return 更新成功返回true
     */
    boolean shipOrder(Integer orderId);

    /**
     * 将商品添加到购物车
     *
     * @param forder  购物车订单
     * @param product 商品信息
     * @return 更新后的购物车订单
     */
    Forder addToCart(Forder forder, Product product);

    /**
     * 从购物车删除商品
     *
     * @param forder    购物车订单
     * @param productId 商品ID
     * @return 更新后的购物车订单
     */
    Forder removeFromCart(Forder forder, Integer productId);

    /**
     * 清空购物车
     *
     * @param forder 购物车订单
     * @return 空的购物车订单
     */
    Forder clearCart(Forder forder);
}

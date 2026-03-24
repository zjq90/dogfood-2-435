package com.example.shop.service.impl;

import com.example.shop.entity.Forder;
import com.example.shop.entity.Product;
import com.example.shop.entity.Sorder;
import com.example.shop.mapper.ForderMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.mapper.SorderMapper;
import com.example.shop.service.ForderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 订单服务实现类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ForderServiceImpl implements ForderService {

    private final ForderMapper forderMapper;
    private final SorderMapper sorderMapper;
    private final ProductMapper productMapper;

    @Override
    public Double calculateTotal(Forder forder) {
        if (forder == null || forder.getOrderItems() == null || forder.getOrderItems().isEmpty()) {
            return 0.0;
        }
        return forder.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getNumber())
                .sum();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitOrder(Forder forder, Forder sessionForder) throws Exception {
        log.info("提交订单开始");

        if (sessionForder == null || sessionForder.getOrderItems() == null || sessionForder.getOrderItems().isEmpty()) {
            log.error("提交订单失败,购物车为空");
            throw new RuntimeException("购物车为空");
        }

        // 检查库存
        for (Sorder item : sessionForder.getOrderItems()) {
            Product product = productMapper.checkStock(item.getProductId(), item.getNumber());
            if (product == null) {
                log.error("提交订单失败,商品库存不足, productId={}", item.getProductId());
                throw new RuntimeException("商品库存不足: " + item.getName());
            }
        }

        // 设置订单总金额
        forder.setTotal(calculateTotal(sessionForder));
        forder.setStatus(Forder.STATUS_PENDING);
        forder.setFdate(new Date());

        // 插入订单
        int result = forderMapper.insert(forder);
        if (result <= 0) {
            log.error("提交订单失败,插入订单异常");
            throw new RuntimeException("订单插入异常");
        }

        Integer orderId = forder.getOrderId();
        log.debug("订单插入成功, orderId={}", orderId);

        // 准备订单项
        List<Sorder> orderItems = new ArrayList<>();
        for (Sorder item : sessionForder.getOrderItems()) {
            item.setOrderId(orderId);
            orderItems.add(item);
        }

        // 批量插入订单项
        if (!orderItems.isEmpty()) {
            result = sorderMapper.batchInsert(orderItems);
            if (result <= 0) {
                log.error("提交订单失败,插入订单项异常");
                throw new RuntimeException("插入订单项异常");
            }
            log.debug("订单项插入成功, count={}", orderItems.size());
        }

        // 扣减库存
        for (Sorder item : sessionForder.getOrderItems()) {
            result = productMapper.deductStock(item.getProductId(), item.getNumber());
            if (result <= 0) {
                log.error("提交订单失败,扣减库存异常, productId={}", item.getProductId());
                throw new RuntimeException("商品库存不足: " + item.getName());
            }
        }

        log.info("提交订单成功, orderId={}", orderId);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> listAllOrders() {
        log.debug("查询所有订单");
        return forderMapper.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Forder findById(Integer orderId) {
        log.debug("查询订单, orderId={}", orderId);
        return forderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> listByUserId(Integer userId) {
        log.debug("查询用户订单, userId={}", userId);
        return forderMapper.selectByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrder(Integer orderId) {
        log.info("删除订单, orderId={}", orderId);

        // 先删除订单项
        sorderMapper.deleteByOrderId(orderId);

        // 再删除订单
        int result = forderMapper.deleteByPrimaryKey(orderId);
        if (result > 0) {
            log.info("删除订单成功, orderId={}", orderId);
            return true;
        }
        log.error("删除订单失败, orderId={}", orderId);
        return false;
    }

    @Override
    public boolean shipOrder(Integer orderId) {
        log.info("发货订单, orderId={}", orderId);
        int result = forderMapper.updateStatusToShipped(orderId);
        if (result > 0) {
            log.info("发货成功, orderId={}", orderId);
            return true;
        }
        log.error("发货失败, orderId={}", orderId);
        return false;
    }

    @Override
    public Forder addToCart(Forder forder, Product product) {
        log.debug("添加商品到购物车, productId={}", product.getProductId());

        if (forder == null) {
            forder = new Forder(new HashSet<>());
        }

        if (forder.getOrderItems() == null) {
            forder.setOrderItems(new HashSet<>());
        }

        // 检查购物车中是否已有该商品
        boolean exists = false;
        for (Sorder item : forder.getOrderItems()) {
            if (item.getProductId().equals(product.getProductId())) {
                // 已存在,增加数量
                item.setNumber(item.getNumber() + product.getQuantity());
                exists = true;
                log.debug("更新购物车商品数量, productId={}, newNumber={}",
                        product.getProductId(), item.getNumber());
                break;
            }
        }

        if (!exists) {
            // 不存在,新增订单项
            Sorder newItem = new Sorder();
            newItem.setName(product.getProductName());
            newItem.setPrice(product.getCostPrice());
            newItem.setNumber(product.getQuantity());
            newItem.setProductId(product.getProductId());
            newItem.setProduct(product);
            forder.getOrderItems().add(newItem);
            log.debug("新增购物车商品, productId={}", product.getProductId());
        }

        // 重新计算总金额
        forder.setTotal(calculateTotal(forder));
        return forder;
    }

    @Override
    public Forder removeFromCart(Forder forder, Integer productId) {
        log.debug("从购物车删除商品, productId={}", productId);

        if (forder == null || forder.getOrderItems() == null) {
            return forder;
        }

        Iterator<Sorder> iterator = forder.getOrderItems().iterator();
        while (iterator.hasNext()) {
            Sorder item = iterator.next();
            if (item.getProductId().equals(productId)) {
                iterator.remove();
                log.debug("删除购物车商品成功, productId={}", productId);
                break;
            }
        }

        // 重新计算总金额
        forder.setTotal(calculateTotal(forder));
        return forder;
    }

    @Override
    public Forder clearCart(Forder forder) {
        log.debug("清空购物车");
        if (forder != null && forder.getOrderItems() != null) {
            forder.getOrderItems().clear();
            forder.setTotal(0.0);
        }
        return forder;
    }
}

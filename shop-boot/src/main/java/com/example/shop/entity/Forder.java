package com.example.shop.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单实体类(主订单)
 * 对应数据库表: zk_forder
 *
 * @author example
 * @version 1.0.0
 */
@Data
public class Forder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单状态: 未发货
     */
    public static final int STATUS_PENDING = 0;

    /**
     * 订单状态: 已发货
     */
    public static final int STATUS_SHIPPED = 1;

    /**
     * 订单状态: 已完成
     */
    public static final int STATUS_COMPLETED = 2;

    /**
     * 订单状态: 已取消
     */
    public static final int STATUS_CANCELLED = 3;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "收货人姓名不能为空")
    private String name;

    /**
     * 收货人电话
     */
    @NotBlank(message = "收货人电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 收货地址
     */
    @NotBlank(message = "收货地址不能为空")
    private String addr;

    /**
     * 订单总金额
     */
    @NotNull(message = "订单金额不能为空")
    private Double total;

    /**
     * 订单状态(0:待发货 1:已发货 2:已完成 3:已取消)
     */
    private Integer status;

    /**
     * 下单时间
     */
    private Date fdate;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单项集合
     */
    private Set<Sorder> orderItems = new HashSet<>();

    public Forder() {
    }

    public Forder(Set<Sorder> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * 计算订单总金额
     */
    public void calculateTotal() {
        if (orderItems != null && !orderItems.isEmpty()) {
            this.total = orderItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getNumber())
                    .sum();
        } else {
            this.total = 0.0;
        }
    }

    /**
     * 添加订单项
     *
     * @param sorder 订单项
     */
    public void addSorder(Sorder sorder) {
        if (sorder != null) {
            sorder.setOrderId(this.orderId);
            this.orderItems.add(sorder);
            calculateTotal();
        }
    }

    /**
     * 移除订单项
     *
     * @param sorder 订单项
     */
    public void removeSorder(Sorder sorder) {
        if (sorder != null && this.orderItems != null) {
            this.orderItems.remove(sorder);
            calculateTotal();
        }
    }

    /**
     * 清空订单项
     */
    public void clearSorders() {
        if (this.orderItems != null) {
            this.orderItems.clear();
        }
        this.total = 0.0;
    }

    /**
     * 获取订单状态文本
     *
     * @return 状态文本
     */
    public String getStatusText() {
        switch (status) {
            case STATUS_PENDING:
                return "待发货";
            case STATUS_SHIPPED:
                return "已发货";
            case STATUS_COMPLETED:
                return "已完成";
            case STATUS_CANCELLED:
                return "已取消";
            default:
                return "未知";
        }
    }
}

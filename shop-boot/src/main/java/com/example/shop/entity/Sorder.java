package com.example.shop.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

/**
 * 订单项实体类(子订单)
 * 对应数据库表: zk_sorder
 *
 * @author example
 * @version 1.0.0
 */
@Data
public class Sorder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单项ID
     */
    private Integer itemId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 商品单价
     */
    @NotNull(message = "商品单价不能为空")
    @Positive(message = "商品单价必须大于0")
    private Double price;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空")
    @Min(value = 1, message = "购买数量至少为1")
    private Integer number;

    /**
     * 所属订单ID
     */
    private Integer orderId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品信息(非数据库字段)
     */
    private Product product;

    public Sorder() {
    }

    public Sorder(String name, Double price, Integer number, Integer orderId, Integer productId) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.orderId = orderId;
        this.productId = productId;
    }

    /**
     * 获取订单项小计金额
     *
     * @return 小计金额
     */
    public Double getSubtotal() {
        return price * number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorder sorder = (Sorder) o;
        return Objects.equals(itemId, sorder.itemId) &&
                Objects.equals(productId, sorder.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, productId);
    }

    @Override
    public String toString() {
        return "Sorder{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}

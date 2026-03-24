package com.example.shop.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品实体类
 * 对应数据库表: zk_product
 *
 * @author example
 * @version 1.0.0
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    /**
     * 销售价
     */
    @NotNull(message = "销售价不能为空")
    @Positive(message = "销售价必须大于0")
    private Double salePrice;

    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空")
    @Positive(message = "成本价必须大于0")
    private Double costPrice;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 是否热门(1:热门 0:普通)
     */
    private Boolean isHot;

    /**
     * 上架日期
     */
    private Date createTime;

    /**
     * 库存数量
     */
    @NotNull(message = "库存数量不能为空")
    @Min(value = 0, message = "库存数量不能小于0")
    private Integer stock;

    /**
     * 购买数量(非数据库字段,用于购物车)
     */
    private Integer quantity;

    public Product() {
    }

    public Product(Integer productId, String productName, Double salePrice, Double costPrice,
                   String picture, String description, Boolean isHot, Date createTime, Integer stock) {
        this.productId = productId;
        this.productName = productName;
        this.salePrice = salePrice;
        this.costPrice = costPrice;
        this.picture = picture;
        this.description = description;
        this.isHot = isHot;
        this.createTime = createTime;
        this.stock = stock;
    }
}

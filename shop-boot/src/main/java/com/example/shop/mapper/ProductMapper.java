package com.example.shop.mapper;

import com.example.shop.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 商品数据访问层
 *
 * @author example
 * @version 1.0.0
 */
@Repository
public interface ProductMapper {

    /**
     * 根据主键删除商品
     *
     * @param productId 商品ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer productId);

    /**
     * 插入商品(全字段)
     *
     * @param product 商品实体
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * 插入商品(选择性)
     *
     * @param product 商品实体
     * @return 影响行数
     */
    int insertSelective(Product product);

    /**
     * 根据主键查询商品
     *
     * @param productId 商品ID
     * @return 商品实体
     */
    Product selectByPrimaryKey(Integer productId);

    /**
     * 根据主键更新商品(选择性)
     *
     * @param product 商品实体
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Product product);

    /**
     * 根据主键更新商品(全字段)
     *
     * @param product 商品实体
     * @return 影响行数
     */
    int updateByPrimaryKey(Product product);

    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    List<Product> selectAll();

    /**
     * 根据商品名称模糊查询
     *
     * @param productName 商品名称关键字
     * @return 商品列表
     */
    List<Product> findByProductNameLike(@Param("productName") String productName);

    /**
     * 查询最新商品(按时间倒序)
     *
     * @param limit 查询数量
     * @return 商品列表
     */
    List<Product> selectNewProducts(@Param("limit") Integer limit);

    /**
     * 查询热门商品
     *
     * @param limit 查询数量
     * @return 商品列表
     */
    List<Product> selectHotProducts(@Param("limit") Integer limit);

    /**
     * 批量扣减商品库存
     *
     * @param products 商品集合
     * @return 影响行数
     */
    int batchDeductStock(@Param("products") Set<Product> products);

    /**
     * 扣减商品库存
     *
     * @param productId 商品ID
     * @param quantity  扣减数量
     * @return 影响行数
     */
    int deductStock(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

    /**
     * 查询库存不足的商品
     *
     * @param productId 商品ID
     * @param quantity  需要数量
     * @return 商品信息
     */
    Product checkStock(@Param("productId") Integer productId, @Param("quantity") Integer quantity);
}

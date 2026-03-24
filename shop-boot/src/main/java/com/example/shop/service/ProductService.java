package com.example.shop.service;

import com.example.shop.entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商品服务接口
 *
 * @author example
 * @version 1.0.0
 */
public interface ProductService {

    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    List<Product> listAllProducts();

    /**
     * 分页查询商品
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageInfo<Product> listByPage(int pageNum, int pageSize);

    /**
     * 根据ID查询商品
     *
     * @param productId 商品ID
     * @return 商品信息
     */
    Product findById(Integer productId);

    /**
     * 添加商品
     *
     * @param product 商品信息
     * @return 添加成功返回true
     */
    boolean addProduct(Product product);

    /**
     * 更新商品
     *
     * @param product 商品信息
     * @return 更新成功返回true
     */
    boolean updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId 商品ID
     * @return 删除成功返回true
     */
    boolean deleteProduct(Integer productId);

    /**
     * 根据名称搜索商品
     *
     * @param productName 商品名称关键字
     * @return 商品列表
     */
    List<Product> searchByName(String productName);

    /**
     * 查询最新商品
     *
     * @param limit 查询数量
     * @return 商品列表
     */
    List<Product> listNewProducts(Integer limit);

    /**
     * 查询热门商品
     *
     * @param limit 查询数量
     * @return 商品列表
     */
    List<Product> listHotProducts(Integer limit);

    /**
     * 扣减库存
     *
     * @param productId 商品ID
     * @param quantity  扣减数量
     * @return 扣减成功返回true
     */
    boolean deductStock(Integer productId, Integer quantity);

    /**
     * 检查库存是否充足
     *
     * @param productId 商品ID
     * @param quantity  需要数量
     * @return 库存充足返回true
     */
    boolean checkStock(Integer productId, Integer quantity);
}

package com.shop.service;

import com.github.pagehelper.PageInfo;
import com.shop.entity.Product;

import java.util.List;

/**
 * 商品服务接口
 * 
 * @author shop
 */
public interface ProductService {

    List<Product> listProduct();

    int addProduct(Product product);

    int deleteProduct(Integer pid);

    Product findById(Integer pid);

    int updateProduct(Product product);

    List<Product> findByName(String pname);

    PageInfo<Product> pageInfo(int pageNum, int pageSize);

    List<Product> frontlistNew();

    List<Product> frontlistHot();
}

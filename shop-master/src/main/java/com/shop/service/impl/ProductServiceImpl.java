package com.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.Product;
import com.shop.mapper.ProductMapper;
import com.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务实现类
 * 
 * @author shop
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> listProduct() {
        logger.debug("查询所有商品列表");
        return productMapper.listProduct();
    }

    @Override
    public int addProduct(Product product) {
        logger.debug("添加商品: {}", product.getPname());
        int result = productMapper.insert(product);
        logger.info("添加商品成功，ID: {}", product.getPid());
        return result;
    }

    @Override
    public int deleteProduct(Integer pid) {
        logger.debug("删除商品: {}", pid);
        int result = productMapper.deleteByPrimaryKey(pid);
        logger.info("删除商品成功: {}", pid);
        return result;
    }

    @Override
    public Product findById(Integer pid) {
        logger.debug("根据ID查询商品: {}", pid);
        return productMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateProduct(Product product) {
        logger.debug("更新商品: {}", product.getPid());
        int result = productMapper.updateByPrimaryKey(product);
        logger.info("更新商品成功: {}", product.getPid());
        return result;
    }

    @Override
    public List<Product> findByName(String pname) {
        logger.debug("根据名称查询商品: {}", pname);
        return productMapper.findByPname(pname);
    }

    @Override
    public PageInfo<Product> pageInfo(int pageNum, int pageSize) {
        logger.debug("分页查询商品，页码: {}, 每页数量: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.listProduct();
        return new PageInfo<>(list);
    }

    @Override
    public List<Product> frontlistNew() {
        logger.debug("查询最新商品");
        return productMapper.frontlistNew();
    }

    @Override
    public List<Product> frontlistHot() {
        logger.debug("查询热门商品");
        return productMapper.frontlistHot();
    }
}

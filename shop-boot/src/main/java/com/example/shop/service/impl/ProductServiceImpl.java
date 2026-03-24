package com.example.shop.service.impl;

import com.example.shop.entity.Product;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务实现类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Product> listAllProducts() {
        log.debug("查询所有商品");
        return productMapper.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<Product> listByPage(int pageNum, int pageSize) {
        log.debug("分页查询商品, pageNum={}, pageSize={}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Integer productId) {
        log.debug("查询商品, productId={}", productId);
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public boolean addProduct(Product product) {
        log.info("添加商品: {}", product.getProductName());
        int result = productMapper.insertSelective(product);
        if (result > 0) {
            log.info("添加商品成功: {}, productId={}", product.getProductName(), product.getProductId());
            return true;
        }
        log.error("添加商品失败: {}", product.getProductName());
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        log.info("更新商品, productId={}", product.getProductId());
        int result = productMapper.updateByPrimaryKeySelective(product);
        if (result > 0) {
            log.info("更新商品成功, productId={}", product.getProductId());
            return true;
        }
        log.error("更新商品失败, productId={}", product.getProductId());
        return false;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        log.info("删除商品, productId={}", productId);
        int result = productMapper.deleteByPrimaryKey(productId);
        if (result > 0) {
            log.info("删除商品成功, productId={}", productId);
            return true;
        }
        log.error("删除商品失败, productId={}", productId);
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchByName(String productName) {
        log.debug("搜索商品, keyword={}", productName);
        return productMapper.findByProductNameLike(productName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listNewProducts(Integer limit) {
        log.debug("查询最新商品, limit={}", limit);
        return productMapper.selectNewProducts(limit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> listHotProducts(Integer limit) {
        log.debug("查询热门商品, limit={}", limit);
        return productMapper.selectHotProducts(limit);
    }

    @Override
    public boolean deductStock(Integer productId, Integer quantity) {
        log.info("扣减库存, productId={}, quantity={}", productId, quantity);
        int result = productMapper.deductStock(productId, quantity);
        if (result > 0) {
            log.info("扣减库存成功, productId={}, quantity={}", productId, quantity);
            return true;
        }
        log.warn("扣减库存失败,库存不足, productId={}, quantity={}", productId, quantity);
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkStock(Integer productId, Integer quantity) {
        log.debug("检查库存, productId={}, quantity={}", productId, quantity);
        Product product = productMapper.checkStock(productId, quantity);
        return product != null;
    }
}

package com.shop.service.impl;

import com.shop.entity.Forder;
import com.shop.entity.Product;
import com.shop.entity.Sorder;
import com.shop.mapper.ForderMapper;
import com.shop.mapper.ProductMapper;
import com.shop.mapper.SorderMapper;
import com.shop.service.ForderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 订单服务实现类
 * 
 * @author shop
 */
@Service
@Transactional
public class ForderServiceImpl implements ForderService {

    private static final Logger logger = LoggerFactory.getLogger(ForderServiceImpl.class);

    @Autowired
    private ForderMapper forderMapper;

    @Autowired
    private SorderMapper sorderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Double calculateTotal(Forder forder) {
        if (forder == null || forder.getSorderSet() == null) {
            return 0.0;
        }
        double total = 0.0;
        for (Sorder sorder : forder.getSorderSet()) {
            total += sorder.getPrice() * sorder.getNumber();
        }
        return total;
    }

    @Override
    public int insertOrder(Forder forder, Forder sessionForder) throws Exception {
        logger.debug("创建订单: {}", forder.getName());

        Set<Sorder> sorderSet = sessionForder.getSorderSet();
        Set<Product> productSet = new HashSet<>();

        for (Sorder sorder : sorderSet) {
            Product product = productMapper.selectByPrimaryKey(sorder.getPid());
            product.setNumber(sorder.getNumber());
            productSet.add(product);
        }

        for (Product product : productSet) {
            Product dbProduct = productMapper.selectByPrimaryKey(product.getPid());
            if (dbProduct.getNumber() < product.getNumber()) {
                logger.warn("商品库存不足: {}", product.getPname());
                throw new RuntimeException("商品 " + product.getPname() + " 库存不足");
            }
        }

        int result = forderMapper.insert(forder);
        logger.info("订单创建成功，订单ID: {}", forder.getFid());

        for (Sorder sorder : sorderSet) {
            sorder.setFid(forder.getFid());
            sorderMapper.insert(sorder);
        }

        return result;
    }

    @Override
    public List<Forder> selectList() {
        logger.debug("查询所有订单");
        return forderMapper.selectList();
    }

    @Override
    public int deleteByPrimaryKey(Integer fid) throws Exception {
        logger.debug("删除订单: {}", fid);

        sorderMapper.deleteByFid(fid);
        int result = forderMapper.deleteByPrimaryKey(fid);
        logger.info("订单删除成功: {}", fid);

        return result;
    }

    @Override
    public int updateStatus(Integer fid, Integer status) {
        logger.debug("更新订单状态，订单ID: {}, 状态: {}", fid, status);
        int result = forderMapper.updateStatus(fid, status);
        logger.info("订单状态更新成功: {}", fid);
        return result;
    }
}

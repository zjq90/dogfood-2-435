package com.shop.service.impl;

import com.shop.entity.Forder;
import com.shop.entity.Product;
import com.shop.entity.Sorder;
import com.shop.mapper.SorderMapper;
import com.shop.service.SorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 订单项服务实现类
 * 
 * @author shop
 */
@Service
@Transactional
public class SorderServiceImpl implements SorderService {

    private static final Logger logger = LoggerFactory.getLogger(SorderServiceImpl.class);

    @Autowired
    private SorderMapper sorderMapper;

    @Override
    public Forder addSorder(Forder forder, Product product) {
        logger.debug("添加商品到购物车: {}", product.getPname());

        Sorder sorder = productToSorder(product);

        Set<Sorder> sorderSet = forder.getSorderSet();
        if (sorderSet == null) {
            sorderSet = new HashSet<>();
            forder.setSorderSet(sorderSet);
        }

        boolean exists = false;
        for (Sorder item : sorderSet) {
            if (item.getPid().equals(product.getPid())) {
                item.setNumber(item.getNumber() + product.getNumber());
                exists = true;
                break;
            }
        }

        if (!exists) {
            sorderSet.add(sorder);
        }

        logger.info("商品添加到购物车成功: {}", product.getPname());
        return forder;
    }

    @Override
    public Sorder productToSorder(Product product) {
        Sorder sorder = new Sorder();
        sorder.setName(product.getPname());
        sorder.setPrice(product.getCprice());
        sorder.setNumber(product.getNumber());
        sorder.setPid(product.getPid());
        sorder.setProduct(product);
        return sorder;
    }

    @Override
    public List<Sorder> listSorderByFid(Integer fid) {
        logger.debug("查询订单项，订单ID: {}", fid);
        return sorderMapper.listSorderByFid(fid);
    }
}

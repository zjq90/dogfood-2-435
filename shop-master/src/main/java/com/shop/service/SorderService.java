package com.shop.service;

import com.shop.entity.Forder;
import com.shop.entity.Product;
import com.shop.entity.Sorder;

import java.util.List;

/**
 * 订单项服务接口
 * 
 * @author shop
 */
public interface SorderService {

    Forder addSorder(Forder forder, Product product);

    Sorder productToSorder(Product product);

    List<Sorder> listSorderByFid(Integer fid);
}

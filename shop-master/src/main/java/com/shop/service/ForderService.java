package com.shop.service;

import com.shop.entity.Forder;

import java.util.List;

/**
 * 订单服务接口
 * 
 * @author shop
 */
public interface ForderService {

    Double calculateTotal(Forder forder);

    int insertOrder(Forder forder, Forder sessionForder) throws Exception;

    List<Forder> selectList();

    int deleteByPrimaryKey(Integer fid) throws Exception;

    int updateStatus(Integer fid, Integer status);
}

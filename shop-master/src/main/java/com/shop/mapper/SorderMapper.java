package com.shop.mapper;

import com.shop.entity.Sorder;

import java.util.List;

/**
 * 订单项数据访问层
 * 
 * @author shop
 */
public interface SorderMapper {

    int deleteByPrimaryKey(Integer sid);

    int insert(Sorder sorder);

    int insertSelective(Sorder sorder);

    Sorder selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Sorder sorder);

    int updateByPrimaryKey(Sorder sorder);

    int deleteByFid(Integer fid);

    List<Sorder> listSorderByFid(Integer fid);
}

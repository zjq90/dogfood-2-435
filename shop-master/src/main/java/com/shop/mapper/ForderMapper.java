package com.shop.mapper;

import com.shop.entity.Forder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单数据访问层
 * 
 * @author shop
 */
public interface ForderMapper {

    int deleteByPrimaryKey(Integer fid);

    int insert(Forder forder);

    int insertSelective(Forder forder);

    Forder selectByPrimaryKey(Integer fid);

    List<Forder> selectList();

    int updateByPrimaryKeySelective(Forder forder);

    int updateByPrimaryKey(Forder forder);

    int updateStatus(@Param("fid") Integer fid, @Param("status") Integer status);
}

package cn.edu.nuc.shop.service;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Product;
import cn.edu.nuc.shop.entity.Sorder;

import java.util.List;

/**
 * 订单明细服务接口
 * 定义订单明细相关业务逻辑接口
 */
public interface SorderService {

    /**
     * 根据ID查询订单明细
     * @param sid 明细ID
     * @return 订单明细对象
     */
    Sorder findById(Integer sid);

    /**
     * 根据订单ID查询订单明细
     * @param fid 订单ID
     * @return 订单明细列表
     */
    List<Sorder> findByFid(Integer fid);

    /**
     * 添加订单明细
     * @param sorder 订单明细对象
     * @return 影响行数
     */
    int addSorder(Sorder sorder);

    /**
     * 批量添加订单明细
     * @param sorderList 订单明细列表
     * @return 影响行数
     */
    int batchAddSorder(List<Sorder> sorderList);

    /**
     * 更新订单明细
     * @param sorder 订单明细对象
     * @return 影响行数
     */
    int update(Sorder sorder);

    /**
     * 删除订单明细
     * @param sid 明细ID
     * @return 影响行数
     */
    int delete(Integer sid);

    /**
     * 根据订单ID删除所有明细
     * @param fid 订单ID
     * @return 影响行数
     */
    int deleteByFid(Integer fid);

    /**
     * 查询所有订单明细
     * @return 订单明细列表
     */
    List<Sorder> findAll();

    /**
     * 根据商品ID查询销售数量
     * @param pid 商品ID
     * @return 销售总数
     */
    int findSalesCountByPid(Integer pid);
    
    /**
     * 添加商品到购物车
     * @param forder 购物车订单
     * @param product 商品信息
     * @return 更新后的购物车订单
     */
    Forder addToCart(Forder forder, Product product);
}

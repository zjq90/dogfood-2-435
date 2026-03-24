package cn.edu.nuc.shop.service;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Sorder;

import java.util.List;
import java.util.Set;

/**
 * 订单服务接口
 * 定义订单相关业务逻辑接口
 */
public interface ForderService {

    /**
     * 根据ID查询订单
     * @param fid 订单ID
     * @return 订单对象
     */
    Forder findById(Integer fid);

    /**
     * 查询所有订单
     * @return 订单列表
     */
    List<Forder> findAll();

    /**
     * 根据用户ID查询订单
     * @param uid 用户ID
     * @return 订单列表
     */
    List<Forder> findByUid(Integer uid);

    /**
     * 根据订单状态查询订单
     * @param status 订单状态
     * @return 订单列表
     */
    List<Forder> findByStatus(Integer status);

    /**
     * 根据用户ID和状态查询订单
     * @param uid 用户ID
     * @param status 订单状态
     * @return 订单列表
     */
    List<Forder> findByUidAndStatus(Integer uid, Integer status);

    /**
     * 创建订单（包括订单明细）
     * @param forder 订单对象
     * @param sorders 订单明细集合
     * @return 订单ID
     */
    Integer createOrder(Forder forder, Set<Sorder> sorders);

    /**
     * 更新订单状态
     * @param fid 订单ID
     * @param status 新状态
     * @return 影响行数
     */
    int updateStatus(Integer fid, int status);

    /**
     * 更新订单信息
     * @param forder 订单对象
     * @return 影响行数
     */
    int update(Forder forder);

    /**
     * 删除订单（包括订单明细）
     * @param fid 订单ID
     * @return 影响行数
     */
    int delete(Integer fid);

    /**
     * 查询订单总数
     * @return 订单总数
     */
    int findCount();
    
    /**
     * 计算购物车总价
     * @param forder 订单对象（购物车）
     * @return 总价
     */
    double calculateTotal(Forder forder);
}

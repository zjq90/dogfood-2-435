package cn.edu.nuc.shop.service.impl;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Sorder;
import cn.edu.nuc.shop.mapper.ForderMapper;
import cn.edu.nuc.shop.mapper.SorderMapper;
import cn.edu.nuc.shop.service.ForderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 订单服务实现类
 * 实现订单相关业务逻辑
 */
@Service
@Transactional
public class ForderServiceImpl implements ForderService {

    private static final Logger logger = LoggerFactory.getLogger(ForderServiceImpl.class);

    @Autowired
    private ForderMapper forderMapper;

    @Autowired
    private SorderMapper sorderMapper;

    @Override
    @Transactional(readOnly = true)
    public Forder findById(Integer fid) {
        logger.debug("根据ID查询订单: {}", fid);
        return forderMapper.selectByPrimaryKey(fid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> findAll() {
        logger.debug("查询所有订单");
        return forderMapper.selectAllForders();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> findByUid(Integer uid) {
        logger.debug("根据用户ID查询订单: {}", uid);
        return forderMapper.selectByUid(uid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> findByStatus(Integer status) {
        logger.debug("根据订单状态查询订单: {}", status);
        return forderMapper.selectByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Forder> findByUidAndStatus(Integer uid, Integer status) {
        logger.debug("根据用户ID和状态查询订单，用户ID: {}, 状态: {}", uid, status);
        return forderMapper.selectByUidAndStatus(uid, status);
    }

    @Override
    public Integer createOrder(Forder forder, Set<Sorder> sorders) {
        logger.info("创建订单，用户ID: {}", forder.getUid());

        // 1. 插入订单主表
        int orderResult = forderMapper.insertSelective(forder);
        if (orderResult <= 0) {
            logger.error("创建订单失败，插入订单主表失败");
            throw new RuntimeException("创建订单失败");
        }

        Integer fid = forder.getFid();
        logger.debug("订单主表创建成功，订单ID: {}", fid);

        // 2. 批量插入订单明细
        if (sorders != null && !sorders.isEmpty()) {
            // 设置订单ID
            for (Sorder sorder : sorders) {
                sorder.setFid(fid);
            }

            List<Sorder> sorderList = new ArrayList<>(sorders);
            int detailResult = sorderMapper.batchInsert(sorderList);

            if (detailResult <= 0) {
                logger.error("创建订单失败，插入订单明细失败，订单ID: {}", fid);
                throw new RuntimeException("创建订单明细失败");
            }
            logger.debug("订单明细创建成功，数量: {}", detailResult);
        }

        logger.info("订单创建成功，订单ID: {}", fid);
        return fid;
    }

    @Override
    public int updateStatus(Integer fid, int status) {
        logger.info("更新订单状态: 订单ID={}, 新状态={}", fid, status);
        int result = forderMapper.updateStatus(fid, status);
        logger.info("更新订单状态{}", result > 0 ? "成功" : "失败");
        return result;
    }

    @Override
    public int update(Forder forder) {
        logger.info("更新订单信息: {}", forder.getFid());
        int result = forderMapper.updateByPrimaryKeySelective(forder);
        logger.info("更新订单信息{}", result > 0 ? "成功" : "失败");
        return result;
    }

    @Override
    public int delete(Integer fid) {
        logger.info("删除订单: {}", fid);

        // 1. 先删除订单明细
        int detailCount = sorderMapper.deleteByFid(fid);
        logger.debug("删除订单明细数量: {}", detailCount);

        // 2. 再删除订单主表
        int orderResult = forderMapper.deleteByPrimaryKey(fid);
        logger.info("删除订单主表{}", orderResult > 0 ? "成功" : "失败");

        return orderResult;
    }

    @Override
    @Transactional(readOnly = true)
    public int findCount() {
        logger.debug("查询订单总数");
        return forderMapper.selectCount();
    }

    @Override
    public double calculateTotal(Forder forder) {
        logger.debug("计算购物车总价");
        if (forder == null || forder.getSorderSet() == null) {
            return 0.0;
        }
        double total = 0.0;
        for (Sorder sorder : forder.getSorderSet()) {
            total += sorder.getPrice() * sorder.getNumber();
        }
        logger.debug("购物车总价: {}", total);
        return total;
    }
}

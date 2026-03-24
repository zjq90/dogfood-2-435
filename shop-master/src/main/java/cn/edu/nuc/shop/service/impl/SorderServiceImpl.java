package cn.edu.nuc.shop.service.impl;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Product;
import cn.edu.nuc.shop.entity.Sorder;
import cn.edu.nuc.shop.mapper.SorderMapper;
import cn.edu.nuc.shop.service.SorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 订单明细服务实现类
 * 实现订单明细相关业务逻辑
 */
@Service
@Transactional
public class SorderServiceImpl implements SorderService {

    private static final Logger logger = LoggerFactory.getLogger(SorderServiceImpl.class);

    @Autowired
    private SorderMapper sorderMapper;

    @Override
    @Transactional(readOnly = true)
    public Sorder findById(Integer sid) {
        logger.debug("根据ID查询订单明细: {}", sid);
        return sorderMapper.selectByPrimaryKey(sid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sorder> findByFid(Integer fid) {
        logger.debug("根据订单ID查询明细: {}", fid);
        return sorderMapper.selectByFid(fid);
    }

    @Override
    public int addSorder(Sorder sorder) {
        logger.info("添加订单明细，商品名称: {}", sorder.getName());
        int result = sorderMapper.insertSelective(sorder);
        logger.info("添加订单明细{}", result > 0 ? "成功" : "失败");
        return result;
    }

    @Override
    public int batchAddSorder(List<Sorder> sorderList) {
        logger.info("批量添加订单明细，数量: {}", sorderList.size());
        int result = sorderMapper.batchInsert(sorderList);
        logger.info("批量添加订单明细{}，实际添加数量: {}", result > 0 ? "成功" : "失败", result);
        return result;
    }

    @Override
    public int update(Sorder sorder) {
        logger.info("更新订单明细: {}", sorder.getSid());
        int result = sorderMapper.updateByPrimaryKeySelective(sorder);
        logger.info("更新订单明细{}", result > 0 ? "成功" : "失败");
        return result;
    }

    @Override
    public int delete(Integer sid) {
        logger.info("删除订单明细: {}", sid);
        int result = sorderMapper.deleteByPrimaryKey(sid);
        logger.info("删除订单明细{}", result > 0 ? "成功" : "失败");
        return result;
    }

    @Override
    public int deleteByFid(Integer fid) {
        logger.info("根据订单ID删除明细: {}", fid);
        int result = sorderMapper.deleteByFid(fid);
        logger.info("删除订单明细数量: {}", result);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sorder> findAll() {
        logger.debug("查询所有订单明细");
        return sorderMapper.selectAllSorders();
    }

    @Override
    @Transactional(readOnly = true)
    public int findSalesCountByPid(Integer pid) {
        logger.debug("查询商品销售数量，商品ID: {}", pid);
        return sorderMapper.selectSalesCountByPid(pid);
    }

    @Override
    public Forder addToCart(Forder forder, Product product) {
        logger.debug("添加商品到购物车，商品ID: {}", product.getPid());
        
        Set<Sorder> sorderSet = forder.getSorderSet();
        boolean isExist = false;
        
        // 检查购物车中是否已有该商品
        for (Sorder sorder : sorderSet) {
            if (sorder.getProduct().getPid().equals(product.getPid())) {
                // 商品已存在，增加数量
                sorder.setNumber(sorder.getNumber() + product.getNumber());
                isExist = true;
                logger.debug("购物车中已有该商品，更新数量: {}", sorder.getNumber());
                break;
            }
        }
        
        if (!isExist) {
            // 商品不存在，创建新的订单项
            Sorder sorder = new Sorder();
            sorder.setName(product.getPname());
            sorder.setPrice(product.getSprice());
            sorder.setNumber(product.getNumber());
            sorder.setProduct(product);
            sorderSet.add(sorder);
            logger.debug("购物车中添加新商品: {}", product.getPname());
        }
        
        return forder;
    }
}

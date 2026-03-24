package com.example.shop.service.impl;

import com.example.shop.entity.Sorder;
import com.example.shop.mapper.SorderMapper;
import com.example.shop.service.SorderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单项服务实现类
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SorderServiceImpl implements SorderService {

    private final SorderMapper sorderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Sorder> listByOrderId(Integer orderId) {
        log.debug("查询订单项列表, orderId={}", orderId);
        return sorderMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public Sorder findById(Integer itemId) {
        log.debug("查询订单项, itemId={}", itemId);
        return sorderMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public boolean deleteItem(Integer itemId) {
        log.info("删除订单项, itemId={}", itemId);
        int result = sorderMapper.deleteByPrimaryKey(itemId);
        if (result > 0) {
            log.info("删除订单项成功, itemId={}", itemId);
            return true;
        }
        log.error("删除订单项失败, itemId={}", itemId);
        return false;
    }
}

package com.shop.controller;

import com.shop.entity.Forder;
import com.shop.service.ForderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

/**
 * 后台订单管理控制器
 * 
 * @author shop
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ForderService forderService;

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public String listProduct(Model model) {
        logger.debug("查询订单列表");
        List<Forder> list = forderService.selectList();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }

    /**
     * 更新订单状态（发货）
     */
    @GetMapping("/updatestatus")
    public String updateStatus(@RequestParam("fid") Integer fid,
                               @RequestParam(value = "status", defaultValue = "1") Integer status,
                               Model model) {
        logger.info("更新订单状态，订单ID: {}, 状态: {}", fid, status);
        try {
            forderService.updateStatus(fid, status);
        } catch (Exception e) {
            logger.error("更新订单状态失败", e);
            model.addAttribute("msg", "发货失败");
            return "error";
        }

        List<Forder> list = forderService.selectList();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }

    /**
     * 删除订单
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("fid") Integer fid, Model model) {
        logger.info("删除订单: {}", fid);
        try {
            forderService.deleteByPrimaryKey(fid);
        } catch (Exception e) {
            logger.error("删除订单失败", e);
            model.addAttribute("msg", "删除失败");
            return "error";
        }

        List<Forder> list = forderService.selectList();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }
}

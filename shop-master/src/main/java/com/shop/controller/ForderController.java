package com.shop.controller;

import com.shop.entity.Forder;
import com.shop.service.ForderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 订单控制器
 * 处理前台订单提交请求
 * 
 * @author shop
 */
@Controller
@RequestMapping("/forder")
public class ForderController {

    private static final Logger logger = LoggerFactory.getLogger(ForderController.class);

    @Autowired
    private ForderService forderService;

    /**
     * 跳转到订单页面
     */
    @GetMapping("/order")
    public String toOrder() {
        return "redirect:/order.jsp";
    }

    /**
     * 提交订单
     */
    @PostMapping("/order")
    public String order(Forder forder, HttpSession session, HttpServletRequest request) {
        logger.info("提交订单: {}", forder.getName());

        Forder sessionForder = (Forder) session.getAttribute("forder");

        try {
            int count = forderService.insertOrder(forder, sessionForder);
            logger.info("订单提交成功，订单ID: {}", forder.getFid());

            request.setAttribute("msg", "提交订单成功");
            session.removeAttribute("forder");

        } catch (Exception e) {
            logger.error("订单提交失败", e);
            request.setAttribute("msg", "商品数量不足");
            return "forward:/msg.jsp";
        }

        return "forward:/msg.jsp";
    }
}

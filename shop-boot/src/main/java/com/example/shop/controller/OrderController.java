package com.example.shop.controller;

import com.example.shop.entity.Forder;
import com.example.shop.entity.Sorder;
import com.example.shop.service.ForderService;
import com.example.shop.service.SorderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * 订单控制器
 * 处理订单相关的请求
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping({"/forder", "/order"})
@RequiredArgsConstructor
public class OrderController {

    private final ForderService forderService;
    private final SorderService sorderService;

    /**
     * 跳转到订单确认页面
     */
    @GetMapping("/order")
    public String toOrder() {
        return "redirect:/order.jsp";
    }

    /**
     * 提交订单
     */
    @PostMapping("/order")
    public String submitOrder(@ModelAttribute Forder forder,
                              HttpSession session,
                              HttpServletRequest request) {
        log.info("提交订单开始");

        Forder cart = (Forder) session.getAttribute("cart");
        if (cart == null || cart.getOrderItems() == null || cart.getOrderItems().isEmpty()) {
            log.warn("提交订单失败,购物车为空");
            request.setAttribute("msg", "购物车为空");
            return "forward:/msg.jsp";
        }

        // 设置用户ID
        Integer userId = (Integer) session.getAttribute("frontUserId");
        forder.setUserId(userId);

        try {
            boolean success = forderService.submitOrder(forder, cart);
            if (success) {
                log.info("订单提交成功, orderId={}", forder.getOrderId());
                // 清空购物车
                session.removeAttribute("cart");
                request.setAttribute("msg", "订单提交成功");
            } else {
                log.error("订单提交失败");
                request.setAttribute("msg", "订单提交失败");
            }
        } catch (Exception e) {
            log.error("订单提交异常: {}", e.getMessage(), e);
            request.setAttribute("msg", e.getMessage());
        }

        return "forward:/msg.jsp";
    }

    /**
     * 后台订单列表
     */
    @GetMapping("/list")
    public String list(Model model) {
        log.debug("查询订单列表");
        List<Forder> list = forderService.listAllOrders();
        // 倒序排列
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }

    /**
     * 发货
     */
    @GetMapping("/ship")
    public String ship(@RequestParam Integer id, Model model) {
        log.info("订单发货, orderId={}", id);

        try {
            boolean success = forderService.shipOrder(id);
            if (success) {
                log.info("订单发货成功, orderId={}", id);
            } else {
                log.error("订单发货失败, orderId={}", id);
                model.addAttribute("msg", "发货失败");
                return "error";
            }
        } catch (Exception e) {
            log.error("订单发货异常: {}", e.getMessage(), e);
            model.addAttribute("msg", "发货失败");
            return "error";
        }

        // 刷新列表
        List<Forder> list = forderService.listAllOrders();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }

    /**
     * 删除订单
     */
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, Model model) {
        log.info("删除订单, orderId={}", id);

        try {
            boolean success = forderService.deleteOrder(id);
            if (!success) {
                model.addAttribute("msg", "删除失败");
                return "error";
            }
            log.info("订单删除成功, orderId={}", id);
        } catch (Exception e) {
            log.error("订单删除异常: {}", e.getMessage(), e);
            model.addAttribute("msg", "删除失败");
            return "error";
        }

        // 刷新列表
        List<Forder> list = forderService.listAllOrders();
        Collections.reverse(list);
        model.addAttribute("list", list);
        return "order/list";
    }

    /**
     * 根据订单ID查询订单项
     */
    @GetMapping("/items")
    public String items(@RequestParam Integer orderId, Model model) {
        log.debug("查询订单项列表, orderId={}", orderId);
        List<Sorder> list = sorderService.listByOrderId(orderId);
        model.addAttribute("list", list);
        model.addAttribute("orderId", orderId);
        return "sorder/list";
    }
}

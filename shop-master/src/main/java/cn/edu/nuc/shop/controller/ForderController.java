package cn.edu.nuc.shop.controller;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Sorder;
import cn.edu.nuc.shop.service.ForderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 订单控制器
 * 处理订单提交、查询等请求
 */
@Controller
@RequestMapping("/forder")
public class ForderController {

    private static final Logger logger = LoggerFactory.getLogger(ForderController.class);

    @Autowired
    private ForderService forderService;

    /**
     * 跳转到订单页面
     * @return 订单页面路径
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String toOrder() {
        logger.debug("跳转到订单页面");
        return "redirect:/order.jsp";
    }

    /**
     * 提交订单
     * @param forder 订单信息
     * @param session Session对象
     * @param request 请求对象
     * @return 消息页面
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(Forder forder, HttpSession session, HttpServletRequest request) {
        logger.info("提交订单请求");

        Forder sessionForder = (Forder) session.getAttribute("forder");

        if (sessionForder == null || sessionForder.getSorderSet() == null || sessionForder.getSorderSet().isEmpty()) {
            logger.warn("购物车为空，无法提交订单");
            request.setAttribute("msg", "购物车为空，请先添加商品");
            return "forward:/msg.jsp";
        }

        try {
            // 设置订单明细
            Set<Sorder> sorders = sessionForder.getSorderSet();
            // 设置订单总额
            forder.setTotal(sessionForder.getTotal());
            // 设置用户ID（从session获取）
            Integer userId = (Integer) session.getAttribute("frontuserId");
            if (userId != null) {
                forder.setUid(userId);
            }

            Integer fid = forderService.createOrder(forder, sorders);
            logger.info("订单提交成功，订单ID: {}", fid);

            request.setAttribute("msg", "提交订单成功");
            session.removeAttribute("forder");

        } catch (Exception e) {
            logger.error("订单提交失败: {}", e.getMessage(), e);
            request.setAttribute("msg", "订单提交失败：" + e.getMessage());
            return "forward:/msg.jsp";
        }

        return "forward:/msg.jsp";
    }
}

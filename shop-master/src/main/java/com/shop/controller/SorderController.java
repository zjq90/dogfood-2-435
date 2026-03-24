package com.shop.controller;

import com.shop.entity.Forder;
import com.shop.entity.Product;
import com.shop.entity.Sorder;
import com.shop.service.ForderService;
import com.shop.service.ProductService;
import com.shop.service.SorderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 购物车控制器
 * 处理购物车相关请求
 * 
 * @author shop
 */
@Controller
@RequestMapping("/sorder")
public class SorderController {

    private static final Logger logger = LoggerFactory.getLogger(SorderController.class);

    @Autowired
    private SorderService sorderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ForderService forderService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public String addSorder(Product product, HttpSession session) {
        logger.info("添加商品到购物车: {}", product.getPid());

        Product findProduct = productService.findById(product.getPid());
        findProduct.setNumber(product.getNumber());

        if (session.getAttribute("forder") == null) {
            session.setAttribute("forder", new Forder(new HashSet<>()));
        }

        Forder forder = (Forder) session.getAttribute("forder");
        forder = sorderService.addSorder(forder, findProduct);
        forder.setTotal(forderService.calculateTotal(forder));

        logger.debug("购物车商品数量: {}", forder.getSorderSet().size());
        return "redirect:/car.jsp";
    }

    /**
     * 从购物车删除商品
     */
    @GetMapping("/delete")
    public String deleteSorder(@RequestParam("pid") Integer pid, HttpSession session) {
        logger.info("从购物车删除商品: {}", pid);

        Forder forder = (Forder) session.getAttribute("forder");
        if (forder == null) {
            return "redirect:/car.jsp";
        }

        Set<Sorder> set = forder.getSorderSet();
        Iterator<Sorder> iterator = set.iterator();

        while (iterator.hasNext()) {
            Sorder sorder = iterator.next();
            if (sorder.getPid().equals(pid)) {
                iterator.remove();
                forder.setTotal(forder.getTotal() - sorder.getPrice() * sorder.getNumber());
            }
        }

        if (set.size() <= 0) {
            session.removeAttribute("forder");
        }

        return "redirect:/car.jsp";
    }

    /**
     * 清空购物车
     */
    @GetMapping("/clear")
    public String clearSorder(HttpSession session) {
        logger.info("清空购物车");
        Forder forder = (Forder) session.getAttribute("forder");
        if (forder != null) {
            forder.getSorderSet().clear();
            forder.setTotal(0.0);
        }
        session.removeAttribute("forder");
        return "redirect:/car.jsp";
    }

    /**
     * 查看订单项
     */
    @GetMapping("/listbyfid")
    public String listSorder(Forder forder, Model model) {
        logger.debug("查看订单项，订单ID: {}", forder.getFid());
        List<Sorder> byFidListSorder = sorderService.listSorderByFid(forder.getFid());
        model.addAttribute("listSorder", byFidListSorder);
        return "sorder/list";
    }
}

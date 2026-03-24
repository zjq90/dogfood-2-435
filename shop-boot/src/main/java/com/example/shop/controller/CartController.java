package com.example.shop.controller;

import com.example.shop.entity.Forder;
import com.example.shop.entity.Product;
import com.example.shop.service.ForderService;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 购物车控制器
 * 处理购物车相关的请求
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ForderService forderService;
    private final ProductService productService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam(defaultValue = "1") Integer quantity,
                            HttpSession session) {
        log.debug("添加商品到购物车, productId={}, quantity={}", productId, quantity);

        // 获取商品信息
        Product product = productService.findById(productId);
        if (product == null) {
            log.warn("添加购物车失败,商品不存在, productId={}", productId);
            return "redirect:/product/frontlist";
        }

        product.setQuantity(quantity);

        // 获取或创建购物车
        Forder cart = (Forder) session.getAttribute("cart");
        if (cart == null) {
            cart = new Forder();
        }

        // 添加商品到购物车
        cart = forderService.addToCart(cart, product);
        session.setAttribute("cart", cart);

        log.info("商品[{}]已添加到购物车", product.getProductName());
        return "redirect:/car.jsp";
    }

    /**
     * 从购物车删除商品
     */
    @GetMapping("/remove")
    public String removeFromCart(@RequestParam Integer productId, HttpSession session) {
        log.debug("从购物车删除商品, productId={}", productId);

        Forder cart = (Forder) session.getAttribute("cart");
        if (cart != null) {
            cart = forderService.removeFromCart(cart, productId);
            if (cart.getOrderItems() == null || cart.getOrderItems().isEmpty()) {
                session.removeAttribute("cart");
            } else {
                session.setAttribute("cart", cart);
            }
        }

        return "redirect:/car.jsp";
    }

    /**
     * 清空购物车
     */
    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        log.info("清空购物车");

        Forder cart = (Forder) session.getAttribute("cart");
        if (cart != null) {
            cart = forderService.clearCart(cart);
            session.removeAttribute("cart");
        }

        return "redirect:/car.jsp";
    }
}

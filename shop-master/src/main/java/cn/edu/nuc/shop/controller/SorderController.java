package cn.edu.nuc.shop.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.entity.Product;
import cn.edu.nuc.shop.entity.Sorder;
import cn.edu.nuc.shop.service.ForderService;
import cn.edu.nuc.shop.service.ProductService;
import cn.edu.nuc.shop.service.SorderService;

/**
 * 购物车订单控制器
 * 处理购物车添加、删除等请求
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
	 * @param product 商品信息（含商品ID和数量）
	 * @param session Session对象
	 * @return 重定向到购物车页面
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addSorder(Product product, HttpSession session) {
        logger.info("添加商品到购物车，商品ID: {}, 数量: {}", product.getPid(), product.getNumber());
		
		// 1:通过product.id获取当前的商品数据
		Product findProduct = productService.findById(product.getPid());
		findProduct.setNumber(product.getNumber());
		
		// 2:判断当前session是否有购物车，如果没有则创建
		if (session.getAttribute("forder") == null) {
            logger.debug("创建新购物车");
			session.setAttribute("forder", new Forder(new HashSet<Sorder>()));
		}
		Forder forder = (Forder) session.getAttribute("forder");
		
		// 3:把商品信息转化为sorder,并且添加到购物车中(判断购物车是否重复)
		forder = sorderService.addToCart(forder, findProduct);
		forder.setTotal(forderService.calculateTotal(forder));
		
		logger.debug("购物车更新成功，总价: {}", forder.getTotal());
		return "redirect:/car.jsp";
	}

	/**
	 * 从购物车删除商品
	 * @param product 商品信息
	 * @param session Session对象
	 * @return 重定向到购物车页面
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteSorder(Product product, HttpSession session) {
		logger.info("从购物车删除商品，商品ID: {}", product.getPid());
		
		Forder forder = (Forder) session.getAttribute("forder");
		if (forder == null) {
			return "redirect:/car.jsp";
		}
		
		Set<Sorder> set = forder.getSorderSet();
		Iterator<Sorder> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			Sorder sorder = iterator.next();
			if (sorder.getProduct().getPid().equals(product.getPid())) {
				iterator.remove();
				forder.setTotal(forder.getTotal() - sorder.getPrice() * sorder.getNumber());
				logger.debug("商品已从购物车移除，商品ID: {}", product.getPid());
			}
		}
		
		if (set.size() <= 0) {
			session.removeAttribute("forder");
			logger.debug("购物车已清空");
		}
		return "redirect:/car.jsp";
	}
	
	/**
	 * 清空购物车
	 * @param session Session对象
	 * @return 重定向到购物车页面
	 */
	@RequestMapping(value="/clear", method=RequestMethod.GET)
	public String clearSorder(HttpSession session) {
		logger.info("清空购物车");
		
		Forder forder = (Forder) session.getAttribute("forder");
		if (forder != null) {
			Set<Sorder> set = forder.getSorderSet();
			set.clear();
			forder.setTotal(0d);
		}
		session.removeAttribute("forder");
		return "redirect:/car.jsp";
	}
	
	/**
	 * 根据订单ID查询订单项
	 * @param forder 订单信息（含订单ID）
	 * @param model 模型对象
	 * @return 订单项列表页面
	 */
	@RequestMapping(value="/listbyfid", method=RequestMethod.GET)
	public String listSorder(Forder forder, Model model) {
		logger.debug("查询订单项，订单ID: {}", forder.getFid());
		
		List<Sorder> byfidlistSorder = sorderService.findByFid(forder.getFid());
		model.addAttribute("listSorder", byfidlistSorder);
		return "sorder/list";
	}
}

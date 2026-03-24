package cn.edu.nuc.shop.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.nuc.shop.entity.Forder;
import cn.edu.nuc.shop.service.ForderService;

/**
 * 后台订单管理控制器
 * 处理后台订单查询、发货、删除等请求
 */
@Controller
@RequestMapping("/order")
public class BKOrderController {

    private static final Logger logger = LoggerFactory.getLogger(BKOrderController.class);
	
	@Autowired
	private ForderService forderService;

	/**
	 * 订单列表
	 * @param model 模型对象
	 * @return 订单列表页面
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listProduct(Model model){
        logger.debug("查询订单列表");
		List<Forder> list = forderService.findAll();
		model.addAttribute("list", list);
		Collections.reverse(list);
        logger.debug("订单数量: {}", list.size());
		return "order/list";
	}

	/**
	 * 更新订单状态（发货）
	 * @param forder 订单信息
	 * @param model 模型对象
	 * @return 订单列表页面
	 */
	@RequestMapping(value = "/updatestatus", method = RequestMethod.GET)
	public String updatestatus(Forder forder, Model model){
		logger.info("更新订单状态，订单ID: {}", forder.getFid());
		try {
			forderService.updateStatus(forder.getFid(), 1);
			logger.info("订单发货成功: {}", forder.getFid());
		} catch (Exception e) {
			logger.error("订单发货失败: {}", e.getMessage());
			model.addAttribute("msg", "发货失败");
			return "error";
		}
		List<Forder> list = forderService.findAll();
		model.addAttribute("list", list);
		Collections.reverse(list);
		return "order/list";
	}
	
	/**
	 * 删除订单
	 * @param forder 订单信息
	 * @param model 模型对象
	 * @return 订单列表页面
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Forder forder, Model model){
		logger.info("删除订单: {}", forder.getFid());
		try {
			forderService.delete(forder.getFid());
			logger.info("订单删除成功: {}", forder.getFid());
		} catch (Exception e) {
			logger.error("订单删除失败: {}", e.getMessage());
			model.addAttribute("msg", "删除失败");
			return "error";
		}
		List<Forder> list = forderService.findAll();
		model.addAttribute("list", list);
		Collections.reverse(list);
		return "order/list";
	}
}

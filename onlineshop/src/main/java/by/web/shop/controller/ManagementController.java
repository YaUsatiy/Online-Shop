package by.web.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.web.shop.shopbackend.dao.CategoryDao;
import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dto.Category;
import by.web.shop.shopbackend.dto.Product;
import by.web.shop.util.FileUtil;
import validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required = false)String operation) {		
		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title", "Product Management");		
		mv.addObject("userClickManageProducts", true);
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully!");
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) {
		new ProductValidator().validate(mProduct, results);
		if (results.hasErrors()) {
			model.addAttribute("title", "Product Management");		
			model.addAttribute("userClickManageProducts", true);
			return "page";
		}
		productDao.add(mProduct);
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUtil.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@ModelAttribute("categories")
	public List<Category> modelCategories() {
		return categoryDao.list();
	}
}

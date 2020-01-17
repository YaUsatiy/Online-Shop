package by.web.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.web.shop.shopbackend.dao.CategoryDao;
import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dto.Category;
import by.web.shop.shopbackend.dto.Product;
import by.web.shop.util.FileUtil;
import validator.ProductImageValidator;

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
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) {
		if (mProduct.getId() == 0) {
			new ProductImageValidator().validate(mProduct, results);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductImageValidator().validate(mProduct, results);
			}
		}
		if (results.hasErrors()) {
			model.addAttribute("title", "Product Management");		
			model.addAttribute("userClickManageProducts", true);
			return "page";
		}
		if (mProduct.getId() == 0) {
			//new product
			productDao.add(mProduct);
		} else {
			//existing product
			productDao.update(mProduct);
		}
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUtil.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		Product product = productDao.get(id);
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Product Management");		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("product", product);
		return mv;
	}
	
	@ModelAttribute("categories")
	public List<Category> modelCategories() {
		return categoryDao.list();
	}
	
	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}
	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {		
		Product product = productDao.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDao.update(product);		
		return (isActive)? "Product Dectivated Successfully!": "Product Activated Successfully";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDao.add(category);
		return "redirect:/manage/products?operation=category";
	}
}

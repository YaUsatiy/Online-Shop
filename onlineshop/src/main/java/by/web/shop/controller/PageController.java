package by.web.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.web.shop.exception.ProductNotFoundException;
import by.web.shop.shopbackend.dao.CategoryDao;
import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dto.Category;
import by.web.shop.shopbackend.dto.Product;

@Controller
public class PageController {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDao.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value = {"/about"})
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = {"/contact"})
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All products");
		mv.addObject("categories", categoryDao.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		Category category = null;
		category = categoryDao.get(id);
		mv.addObject("title", category.getName());
		mv.addObject("category", category);
		mv.addObject("categories", categoryDao.list());
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	@RequestMapping(value = {"/show/{id}/product"})
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		Product product = null;
		product = productDao.get(id);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		product.setViews(product.getViews() + 1);
		productDao.update(product);
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}	
	
	@RequestMapping(value = {"/login"})
	public ModelAndView login(@RequestParam(name="error", required = false)String error) {
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid username or password!");
		}
		mv.addObject("title", "Login");
		return mv;
	}
	
	@RequestMapping(value = {"/access-denied"})
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Good try, rogue");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}	
}




























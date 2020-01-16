package by.web.shop.shopbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDao productDao;
	private static Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("by.web.shop.shopbackend");
		context.refresh();
		productDao = (ProductDao) context.getBean("productDao");
	}
	
	@Test
	public void testCRUDProduct() {
		// create operation
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phones!");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals(true, productDao.add(product));		
		
		// reading and updating the category
		product = productDao.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals(true, productDao.update(product));		
				
		assertEquals(true, productDao.delete(product));		
		
		// list
		assertEquals(6, productDao.list().size());		
				
	}
	
	@Test
	public void testListActiveProducts() {
		assertEquals(5, productDao.listActiveProducts().size());				
	} 
	
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals(3, productDao.listActiveProductsByCategory(3).size());
		assertEquals(2, productDao.listActiveProductsByCategory(1).size());
	} 
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals(3, productDao.getLatestActiveProducts(3).size());
	} 
}

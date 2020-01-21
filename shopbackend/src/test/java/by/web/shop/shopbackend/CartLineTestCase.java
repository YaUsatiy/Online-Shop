package by.web.shop.shopbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.web.shop.shopbackend.dao.CartLineDao;
import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dao.UserDao;
import by.web.shop.shopbackend.dto.Cart;
import by.web.shop.shopbackend.dto.CartLine;
import by.web.shop.shopbackend.dto.Product;
import by.web.shop.shopbackend.dto.User;

public class CartLineTestCase {
	private static AnnotationConfigApplicationContext context;

	private static CartLineDao cartLineDao;
	private static ProductDao productDao;
	private static UserDao userDao;
	
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("by.web.shop.shopbackend");
		context.refresh();
		cartLineDao = (CartLineDao)context.getBean("cartLineDao");
		productDao = (ProductDao)context.getBean("productDao");
		userDao = (UserDao)context.getBean("userDao");
	}
	
	@Test
	public void testAddCartLine() {
		User user = userDao.getByEmail("bb@gmail.com");		
		Cart cart = user.getCart();
		Product product = productDao.get(2);
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);
		cartLine.setBuyingPrice(product.getUnitPrice());
		double oldTotal = cartLine.getTotal();		
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		assertEquals(true, cartLineDao.add(cartLine));
		assertEquals(true, cartLineDao.updateCart(cart));
		
	}
	
	@Test
	public void testUpdateCartLine() {
		User user = userDao.getByEmail("bb@gmail.com");		
		Cart cart = user.getCart();
		cartLine = cartLineDao.getByCartAndProduct(cart.getId(), 2);
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		double oldTotal = cartLine.getTotal();
		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		assertEquals(true, cartLineDao.update(cartLine));	
	}
}

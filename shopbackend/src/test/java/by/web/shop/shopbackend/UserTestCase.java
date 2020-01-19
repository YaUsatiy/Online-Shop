package by.web.shop.shopbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.web.shop.shopbackend.dao.UserDao;
import by.web.shop.shopbackend.dto.Address;
import by.web.shop.shopbackend.dto.Cart;
import by.web.shop.shopbackend.dto.User;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDao userDao;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("by.web.shop.shopbackend");
		context.refresh();
		userDao = (UserDao) context.getBean("userDao");
	}
	
	@Test
	public void testAddUser() {	
		user = new User() ;
		user.setFirstName("Filya");
		user.setLastName("Mark");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		address = new Address();
		address.setAddressLineOne("Lithuanian Embassy");
		address.setAddressLineTwo("Uncle Ben's house");
		address.setCity("Minsk");
		address.setState("Minskaya");
		address.setCountry("Belarus");
		address.setPostalCode("222160");
		address.setBilling(true);
		
		// linked the address with the user
		address.setUserId(user.getId());
		
		cart = new Cart();
		// linked the cart with the user
		cart.setUser(user);
		// link the user with the cart
		user.setCart(cart);
		// add the user
		assertEquals(true, userDao.addUser(user));	
		// add the address
		assertEquals(true, userDao.addAddress(address));

				
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("Sovetskaya, 47");
		address.setAddressLineTwo("Sovetskaya, 18");
		address.setCity("Zhodino");
		address.setState("Minskaya");
		address.setCountry("Belarus");
		address.setPostalCode("222163");
		address.setShipping(true);
		address.setUserId(user.getId());
		assertEquals(true, userDao.addAddress(address));
		
	}
}

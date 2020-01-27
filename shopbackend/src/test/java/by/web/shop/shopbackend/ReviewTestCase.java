package by.web.shop.shopbackend;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dao.ReviewDao;
import by.web.shop.shopbackend.dao.UserDao;
import by.web.shop.shopbackend.dto.Review;
import by.web.shop.shopbackend.dto.User;

public class ReviewTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ReviewDao reviewDao;
	private static UserDao userDao;
	private static ProductDao productDao;
	private static Review review;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("by.web.shop.shopbackend");
		context.refresh();
		reviewDao = (ReviewDao) context.getBean("reviewDao");
		productDao = (ProductDao) context.getBean("productDao");
		userDao = (UserDao) context.getBean("userDao");
	}
	
	@Test
	public void testAddReview() {
		review = new Review();
		review.setProductId(1);
		review.setComment("Nice Product! I appreciate it Again)");
		review.setRating(4);
		User user = userDao.getByEmail("bb@gmail.com");
		review.setUserId(user.getId());
		review.setReviewDate(new Date());
		assertEquals(true, reviewDao.add(review));
	}

}

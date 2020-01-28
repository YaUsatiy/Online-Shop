package by.web.shop.service;

import java.util.List;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.web.shop.shopbackend.dao.ProductDao;
import by.web.shop.shopbackend.dto.Product;
import by.web.shop.shopbackend.dto.Review;

@Service("productService")
public class ProductService {
	@Autowired
	private ProductDao productDao;
	private static final int DECIMAL_PLACES_KOEFFICIENT = 10; 
	
	public void updateRatingAdd(Product product) {
		List<Review> reviews = productDao.getProductReviews(product);
		OptionalDouble rating = reviews.stream().mapToInt(a -> a.getRating()).average();
		product.setRating(Math.round(rating.getAsDouble() * DECIMAL_PLACES_KOEFFICIENT) / DECIMAL_PLACES_KOEFFICIENT);
		product.setReviewCount(product.getReviewCount() + 1);
		productDao.update(product);
	}
	
	public void updateRatingDelete(Product product) {
		List<Review> reviews = productDao.getProductReviews(product);
		OptionalDouble rating = reviews.stream().mapToInt(a -> a.getRating()).average();
		product.setRating(Math.round(rating.getAsDouble() * DECIMAL_PLACES_KOEFFICIENT) / DECIMAL_PLACES_KOEFFICIENT);
		product.setReviewCount(product.getReviewCount() - 1);
		productDao.update(product);
	}
}

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
	ProductDao productDao;
	
	public void updateRating(int productId) {
		List<Review> reviews = productDao.getProductReviews(productId);
		OptionalDouble rating = reviews.stream().mapToInt(a -> a.getRating()).average();
		Product product = productDao.get(productId);
		product.setRating(Math.round(rating.getAsDouble()));
		productDao.update(product);
	}
}

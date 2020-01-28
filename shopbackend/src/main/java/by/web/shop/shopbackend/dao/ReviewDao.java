package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Product;
import by.web.shop.shopbackend.dto.Review;
import by.web.shop.shopbackend.dto.User;

public interface ReviewDao {
	Review get(int id);
	List<Review> list();
	boolean add(Review review);
	boolean delete(Review review);
	
	List<Review> listByProduct(Product product);
	List<Review> listByUser(User user);
}

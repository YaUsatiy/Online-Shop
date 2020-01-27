package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Review;

public interface ReviewDao {
	Review get(int id);
	List<Review> list();
	boolean add(Review review);
	boolean delete(Review review);
	
	List<Review> listByProduct(int productId);
	List<Review> listByUser(int userId);
}

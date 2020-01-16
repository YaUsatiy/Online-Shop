package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Category;

public interface CategoryDao {
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}

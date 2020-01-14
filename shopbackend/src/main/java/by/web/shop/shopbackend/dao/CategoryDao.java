package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Category;

public interface CategoryDao {
	List<Category> list();
	Category get(int id);
}

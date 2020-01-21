package by.web.shop.shopbackend;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import by.web.shop.shopbackend.dao.CategoryDao;
import by.web.shop.shopbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDao categoryDao;
	private static Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("by.web.shop.shopbackend");
		context.refresh();
		categoryDao = (CategoryDao) context.getBean("categoryDao");
	}

	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_105.png");
		assertEquals(true, categoryDao.add(category));
	}

	@Test
	public void testGetCategory() {
		category = categoryDao.get(1);
		assertEquals("Laptop", category.getName());
	}

	@Test
	public void testUpdateCategory() {
		category = categoryDao.get(1);
		category.setName("NewName2");
		assertEquals(true, categoryDao.update(category));
	}

	@Test
	public void testDeleteCategory() {
		category = categoryDao.get(2);
		assertEquals(true, categoryDao.delete(category));
	}

	@Test
	public void testListCategory() {
		List<Category> categories = categoryDao.list();
		assertEquals(1, categories.size());
	}

	@Test
	public void createCRUDCategory() {
		// add operation
		category = new Category();

		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

		category = new Category();
		category.setName("Television");
		category.setDescription("This is some description for television!");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

		// fetching and updating the category
		category = categoryDao.get(2);
		category.setName("TV");
		assertEquals("Successfully updated a single category in the table!", true, categoryDao.update(category));

		// delete the category
		assertEquals("Successfully deleted a single category in the table!", true, categoryDao.delete(category));

		// fetching the list
		assertEquals("Successfully fetched the list of categories from the table!", 1, categoryDao.list().size());
	}

}

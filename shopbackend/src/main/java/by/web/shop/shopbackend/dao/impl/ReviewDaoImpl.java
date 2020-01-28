package by.web.shop.shopbackend.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.web.shop.shopbackend.dao.ReviewDao;
import by.web.shop.shopbackend.dto.Product;
import by.web.shop.shopbackend.dto.Review;
import by.web.shop.shopbackend.dto.User;

@Repository("reviewDao")
@Transactional
public class ReviewDaoImpl implements ReviewDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Review get(int id) {
		return sessionFactory.getCurrentSession().get(Review.class, id);
	}

	@Override
	public List<Review> list() {
		return sessionFactory.getCurrentSession().createQuery("From Review", Review.class).getResultList();
	}

	@Override
	public boolean add(Review review) {
		try {
			sessionFactory.getCurrentSession().save(review);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Review review) {
		try {
			sessionFactory.getCurrentSession().delete(review);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Review> listByProduct(Product product) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Review WHERE product = :product", Review.class)
						.setParameter("product", product)
								.getResultList();
	}
	
	@Override
	public List<Review> listByUser(User user) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Review WHERE user = :user", Review.class)
						.setParameter("user", user)
								.getResultList();
	}

}

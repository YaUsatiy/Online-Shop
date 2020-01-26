package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.OrderDetail;

public interface OrderDetailDao {
	public OrderDetail get(int id);
	public List<OrderDetail> list();
	public boolean add(OrderDetail orderDetail);

}

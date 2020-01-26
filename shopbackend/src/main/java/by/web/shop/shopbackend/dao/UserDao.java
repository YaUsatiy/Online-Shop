package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Address;
import by.web.shop.shopbackend.dto.OrderDetail;
import by.web.shop.shopbackend.dto.User;

public interface UserDao {
	boolean addUser(User user);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	User getByEmail(String email);
	List<Address> listShippingAddresses(int userId);
	Address getBillingAddress(int userId);
	User get(int id);
	Address getAddress(int addressId);
	List<OrderDetail> getOrders(User user);
}

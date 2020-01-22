package by.web.shop.shopbackend.dao;

import java.util.List;

import by.web.shop.shopbackend.dto.Cart;
import by.web.shop.shopbackend.dto.CartLine;
import by.web.shop.shopbackend.dto.OrderDetail;

public interface CartLineDao {
	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	public CartLine getByCartAndProduct(int cartId, int productId);		
	boolean updateCart(Cart cart);
	public List<CartLine> listAvailable(int cartId);
	public boolean addOrderDetail(OrderDetail orderDetail);
}

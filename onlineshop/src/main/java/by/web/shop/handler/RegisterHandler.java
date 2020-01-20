package by.web.shop.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import by.web.shop.model.RegisterModel;
import by.web.shop.shopbackend.dao.UserDao;
import by.web.shop.shopbackend.dto.Address;
import by.web.shop.shopbackend.dto.Cart;
import by.web.shop.shopbackend.dto.User;

@Component
public class RegisterHandler {
	@Autowired
	UserDao userDao;
	private final String TRANSITIONAL_SUCCESS_VALUE = "success";
	private final String TRANSITIONAL_FAILURE_VALUE = "failure";
	private final String ERROR_MESSAGE_PASSWORD = "Password does not match confirm password!";
	private final String ERROR_MESSAGE_EMAIL = "Email address is already taken!";
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel model, User user) {
		model.setUser(user);
	}
	
	public void addBilling(RegisterModel model, Address billing) {
		model.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model) {
		User user = model.getUser();
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		userDao.addUser(user);
		Address billing = model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		userDao.addAddress(billing);
		return TRANSITIONAL_SUCCESS_VALUE;
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionalValue = TRANSITIONAL_SUCCESS_VALUE;
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			error.addMessage(new MessageBuilder().error().
					source("confirmPassword").defaultText(ERROR_MESSAGE_PASSWORD).build());
			transitionalValue = TRANSITIONAL_FAILURE_VALUE;
		}
		if (userDao.getByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder().error().
					source("email").defaultText(ERROR_MESSAGE_EMAIL).build());
			transitionalValue = TRANSITIONAL_FAILURE_VALUE;
		}
		return transitionalValue;
	}
	
}

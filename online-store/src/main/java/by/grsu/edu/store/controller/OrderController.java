package by.grsu.edu.store.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.service.IOrderService;
import by.grsu.edu.store.responce.MyResponse;
import by.grsu.edu.store.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private static final String TOKEN = "token";

	private static Logger logger = LogManager.getLogger(UserController.class);

	private IOrderService orderService;

	public OrderController() {
		try {
			orderService = new OrderService();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			logger.log(Level.INFO, e.getMessage(), e);
		}
	}

	@GetMapping("/{userId}")
	public Object getOrders(@PathVariable Long userId) throws SQLException, DaoException {
		return new MyResponse(orderService.getOrdersForUser(userId));
	}

	@PostMapping("/{productId}")
	public Object makeOrder(@PathVariable Long productId, HttpServletRequest request) throws SQLException, DaoException {
		
		Long userId = TokenMap.getTokenMap().get(TOKEN);
		orderService.makeOrder(userId, productId);
		return new MyResponse();
	}
}

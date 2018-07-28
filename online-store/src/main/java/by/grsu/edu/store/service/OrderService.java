package by.grsu.edu.store.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IOrderDao;
import by.grsu.edu.store.api.dao.ext.IProductDao;
import by.grsu.edu.store.api.dao.ext.IUserDao;
import by.grsu.edu.store.api.service.IOrderService;
import by.grsu.edu.store.dao.Connector;
import by.grsu.edu.store.dao.ext.OrderDaoImpl;
import by.grsu.edu.store.dao.ext.ProductDaoImpl;
import by.grsu.edu.store.dao.ext.UserDaoImpl;
import by.grsu.edu.store.entity.Order;
import by.grsu.edu.store.entity.Product;
import by.grsu.edu.store.entity.User;

public class OrderService implements IOrderService {
	private static Logger logger = LogManager.getLogger(OrderService.class);
	
	private Connection connection;

	private IUserDao userDao;

	private IOrderDao orderDao;

	private IProductDao productDao;

	public OrderService() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		connection = Connector.getConnection();
		userDao = new UserDaoImpl();
		orderDao = new OrderDaoImpl();
		productDao = new ProductDaoImpl();
	}

	@Override
	public Order get(Long id) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			Order order = orderDao.get(connection, id);
			if (order == null) {
				throw new DaoException("Order does not exist");
			}
			connection.commit();
			return order;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public List<Order> getAll() throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			List<Order> orders = orderDao.getAll(connection);
			connection.commit();
			return orders;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public List<Order> getOrdersForUser(Long userId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			List<Order> orders = orderDao.getUserOrders(connection, userId);
			connection.commit();
			return orders;
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}

	@Override
	public void makeOrder(Long userId, Long productId) throws SQLException, DaoException {
		try {
			connection.setAutoCommit(false);
			User user = userDao.get(connection, userId);
			if(user == null) {
				throw new DaoException("User does not exist");
			}
			Product product = productDao.get(connection, productId);
			if(product == null) {
				throw new DaoException("Product does not exist");
			}
			Order order = new Order();
			order.setDate(new Date());
			order.setUser(user);
			order.setProduct(product);
		} catch (Exception e) {
			connection.rollback();
			logger.log(Level.INFO, e.getMessage(), e);
			throw new DaoException(e);
		} finally {
			connection.setAutoCommit(true);
		}
	}
}

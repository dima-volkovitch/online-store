package by.grsu.edu.store.api.service;

import java.sql.SQLException;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.Order;

public interface IOrderService {

	Order get(Long id) throws SQLException, DaoException;

	List<Order> getAll() throws SQLException, DaoException;

	List<Order> getOrdersForUser(Long userId) throws SQLException, DaoException;

	void makeOrder(Long userId, Long productId) throws SQLException, DaoException;

}

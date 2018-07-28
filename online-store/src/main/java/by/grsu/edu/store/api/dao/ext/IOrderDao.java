package by.grsu.edu.store.api.dao.ext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import by.grsu.edu.store.api.dao.GenericDao;
import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.entity.Order;

public interface IOrderDao extends GenericDao<Order, Long> {

	List<Order> getUserOrders(Connection connection, Long userId) throws SQLException, DaoException;

}

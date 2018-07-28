package by.grsu.edu.store.dao.ext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IOrderDao;
import by.grsu.edu.store.dao.AbstractDao;
import by.grsu.edu.store.entity.Order;

public class OrderDaoImpl extends AbstractDao<Order, Long> implements IOrderDao {
	private static final String SELECT_QUERY = "SELECT * FROM online_store.orders";
	private static final String PERSIST_QUERY = "INSERT INTO online_store.orders(date, user_id, product_id) VALUES(?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE online_store.orders SET date=?, user_id=?, product_id=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM online_store.orders WHERE id=?;";

	private static final String ID = "id";
	private static final String DATE = "date";
	private static final String USER_ID = "user_id";
	private static final String PRODUCT_ID = "product_id";

	@Override
	protected String getSelectQuery() {
		return SELECT_QUERY;
	}

	@Override
	protected String getPersistQuery() {
		return PERSIST_QUERY;
	}

	@Override
	protected String getUpdateQuery() {
		return UPDATE_QUERY;
	}

	@Override
	protected String getDeleteQuery() {
		return DELETE_QUERY;
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws DaoException {
		try {
			List<Order> list = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getLong(ID));
				order.setDate(rs.getDate(DATE));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException();
		}
	}

	@Override
	protected void prepareStatementForPersist(PreparedStatement statement, Order entity) throws DaoException {
		try {
			statement.setDate(1, new Date(entity.getDate().getTime()));
			if (entity.getUser() != null) {
				statement.setLong(2, entity.getUser().getId());
			} else {
				statement.setString(2, null);
			}
			if (entity.getProduct() != null) {
				statement.setLong(3, entity.getProduct().getId());
			} else {
				statement.setString(3, null);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Order entity) throws DaoException {
		try {
			statement.setDate(1, new Date(entity.getDate().getTime()));
			if (entity.getUser() != null) {
				statement.setLong(2, entity.getUser().getId());
			} else {
				statement.setString(2, null);
			}
			if (entity.getProduct() != null) {
				statement.setLong(3, entity.getProduct().getId());
			} else {
				statement.setString(3, null);
			}
			statement.setLong(4, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, Order entity) throws DaoException {
		try {
			statement.setLong(1, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Order> getUserOrders(Connection connection, Long userId) throws SQLException, DaoException {
		String sql = "SELECT * FROM online_store.orders WHERE user_id=?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, userId);
			ResultSet rs = statement.executeQuery();
			return parseResultSet(rs);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}

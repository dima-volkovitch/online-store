package by.grsu.edu.store.dao.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import by.grsu.edu.store.api.dao.exceptions.DaoException;
import by.grsu.edu.store.api.dao.ext.IUserDao;
import by.grsu.edu.store.dao.AbstractDao;
import by.grsu.edu.store.entity.User;
import by.grsu.edu.store.entity.UserStatus;

public class UserDaoImpl extends AbstractDao<User, Long> implements IUserDao {
	private static final String SELECT_QUERY = "SELECT * FROM online_store.users";
	private static final String PERSIST_QUERY = "INSERT INTO online_store.users(number, password, user_status) VALUES(?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE online_store.users SET number=?, password=?, user_status=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM online_store.users WHERE id=?;";

	private static final String ID = "id";
	private static final String NUMBER = "number";
	private static final String PASSWORD = "password";
	private static final String USER_STATUS = "user_status";

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
	protected List<User> parseResultSet(ResultSet rs) throws DaoException {
		try {
			List<User> list = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong(ID));
				user.setNumber(rs.getString(NUMBER));
				user.setPassword(rs.getString(PASSWORD));
				user.setUserStatus(Enum.valueOf(UserStatus.class, rs.getString(USER_STATUS)));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForPersist(PreparedStatement statement, User entity) throws DaoException {
		try {
			statement.setString(1, entity.getNumber());
			statement.setString(2, entity.getPassword());
			statement.setString(3, entity.getUserStatus().toString());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws DaoException {
		try {
			statement.setString(1, entity.getNumber());
			statement.setString(2, entity.getPassword());
			statement.setString(3, entity.getUserStatus().toString());
			statement.setLong(4, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement, User entity) throws DaoException {
		try {
			statement.setLong(1, entity.getId());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public User get(Connection connection, String number) throws DaoException {
		String sql = "SELECT * FROM online_store.users WHERE number=?;";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, number);
			ResultSet rs = statement.executeQuery();
			return parseResultSet(rs).get(0);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
